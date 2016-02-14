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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.WalletModel;
import stand.lemonade.service.WalletService;
import stand.lemonade.util.LemonadeValidator;

@Path("/api/wallets")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class WalletResource {
	@Inject
	private WalletService walletService;

	@Inject
	private LemonadeValidator validator;

	@GET
	public Response getAllWallets(@Auth User user) throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			List<WalletModel> ret = walletService.getAllWallets();

			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@Path("/available")
	@GET
	public Response getAvailableWallets(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			List<WalletModel> ret = walletService.getAvailableWallets();

			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	public Response createWallet(@Auth User user, WalletModel model) throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			validator.beanValidation(model);

			WalletModel modelCreated = walletService.createWallet(model);

			return Response.status(201).entity(modelCreated).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/{id}")
	public Response getWalletById(@Auth User user, @PathParam("id") LongParam id) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			return Response.ok().entity(walletService.getWalletById(id.get())).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}

	}

	@PUT
	@Path("/{id}")
	public Response updateWallet(@Auth User user, @PathParam("id") LongParam id, WalletModel model)
			throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			validator.beanValidation(model);

			model.setId(id.get());

			WalletModel modelUpdated = walletService.updateWallet(model);
			return Response.ok().entity(modelUpdated).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}

	}

	@DELETE
	@Path("/{id}")
	public Response deleteWallet(@Auth User user, @PathParam("id") LongParam id) throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			walletService.deleteWallet(id.get());

			return Response.status(204).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}
}
