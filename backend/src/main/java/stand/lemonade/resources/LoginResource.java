package stand.lemonade.resources;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import stand.lemonade.config.LemonadeAuthenticator;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.LoginModel;
import stand.lemonade.service.UserService;
import stand.lemonade.util.LemonadeValidator;

@Path("/api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

	@Inject
	private UserService userService;

	@Inject
	private LemonadeValidator validator;

	@POST
	public LoginModel login(LoginModel loginModel, @Context HttpServletResponse response) throws LemonadeException {
		validator.beanValidation(loginModel);

		/* check user and password match and if user is active */
		LoginModel loginCheck = userService.checkUser(loginModel);

		User user = new User(); // this is the user from the database
		user.setEmail(loginCheck.getEmail());
		user.setId(loginCheck.getUserId());
		user.setRole(Roles.getRoleFromString(loginCheck.getRole()));

		// jwtToken is what the service return to the Resource (or even better,
		// a LoginModel in which we include all the info they want to know at
		// login => role, first name, last name, etc)
		String jwtToken = LemonadeAuthenticator.generateWebToken(user);

		// HashMap<String, String> response = new HashMap<String, String>();
		// response.put("token", jwtToken);
		// response.put("role", loginCheck.getRole());
		// response.put("firstName", loginCheck.getFirstName());
		// response.put("lastName", loginCheck.getLastName());
		// response.put("email",loginCheck.getEmail());

		response.setHeader("X-AUTH-TOKEN", jwtToken);
		response.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN");

		loginCheck.setToken(jwtToken);
		return loginCheck;
	}
}