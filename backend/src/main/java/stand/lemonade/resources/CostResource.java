package stand.lemonade.resources;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import stand.lemonade.models.CostModel;
import stand.lemonade.service.CostService;
import stand.lemonade.service.ShiftService;
import stand.lemonade.util.LemonadeValidator;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("api/expense")
public class CostResource {
	@Inject
	private CostService costService;

	@Inject
	private LemonadeValidator validator;

	@Inject
	private ShiftService shiftService;

	@GET
	public Response getAllCosts(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			List<CostModel> ret = costService.getAllCosts();
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/{shiftId}")
	public Response getCostByShiftId(@Auth User user, @PathParam("shiftId") LongParam shiftId)
			throws LemonadeException {

		if (Roles.isValidRole(user.getRole().toString())) {
			List<CostModel> ret = costService.getAllCostsByShiftId(shiftId.get());
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	public Response createCost(@Auth User user, CostModel costModel) throws LemonadeException {
		validator.beanValidation(costModel);
		/* only waiter can create a new cost */
		if (user.getRole().equals(Roles.WAITER)) {
			validator.beanValidation(costModel);

			costModel.setShiftId(shiftService.getShiftByUserId(user.getId()).getId());
			CostModel ret = costService.createCosts(costModel);
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}
}
