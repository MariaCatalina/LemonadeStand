package stand.lemonade.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.common.base.Optional;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.ProfileImageModel;
import stand.lemonade.models.UserModel;
import stand.lemonade.service.ProfileImageService;
import stand.lemonade.service.UserService;
import stand.lemonade.util.LemonadeValidator;

@Path("/api/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

	@Inject
	private UserService userService;
	
	@Inject
	private ProfileImageService profileImageService;

	@Inject
	private LemonadeValidator validator;

	@GET
	public Response getAllUsers(@Auth User user, @QueryParam("role") Optional<String> role) throws LemonadeException {

		List<UserModel> ret = null;
		final String roleName = role.orNull();
		Roles roleEnum = Roles.getRoleFromString(roleName);

		if (Roles.isValidRole(user.getRole().toString())) {

			if (roleEnum != null) {
				ret = userService.getUsers(roleEnum);
			} else {
				ret = userService.getUsers();
			}
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	public Response createUser(@Auth User user, UserModel userModel) throws LemonadeException {
		/* only admin users are allowed to create other users */
		if (user.getRole().equals(Roles.ADMIN)) {
			UserModel ret = userService.createUser(userModel);
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/{id}")
	public Response getUserById(@Auth User user, @PathParam("id") LongParam id) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			UserModel ret = userService.getUserById(id.get());
			return Response.ok().entity(ret).build();

		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@PUT
	@Path("/{id}")
	public Response updateUserById(@Auth User user, @PathParam("id") LongParam id, UserModel userModel)
			throws LemonadeException {
		/* only admin users are allowed to create other users */
		if (user.getRole().equals(Roles.ADMIN)) {
			validator.beanValidation(userModel);

			UserModel ret = userService.updateUser(userModel);
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@DELETE
	@Path("/{id}")
	public void deleteUserById(@Auth User user, @PathParam("id") LongParam id) throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			userService.deleteUser(id.get());
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}
	
	@GET
	@Path("/profileimage")
	public Response getUserProfileImage(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			ProfileImageModel model = profileImageService.getProfileImage(user.getId().longValue());
			
			return Response.ok().entity(model).build();

		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}
	
	@POST
	@Path("/profileimage")
	public Response createUserProfileImage(@Auth User user, ProfileImageModel model) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			model.setId(user.getId().longValue());
			ProfileImageModel ret = profileImageService.createProfileImage(user.getId().longValue(), model);
			
			return Response.ok().entity(ret).build();

		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/userProfile")
	public Response getCurrentUserProfile(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			UserModel ret = userService.getUserById(user.getId());
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@PUT
	@Path("/userProfile")
	public Response editProfile(@Auth User user, UserModel userModel) throws LemonadeException {
		/* check whether the user is right role */
		if (Roles.isValidRole(user.getRole().toString())) {
			validator.beanValidation(userModel);

			UserModel ret = userService.updateUserProfile(userModel);
			return Response.ok().entity(ret).build();

		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

}
