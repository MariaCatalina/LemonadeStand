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
import stand.lemonade.models.IncomeModel;
import stand.lemonade.service.IncomeService;
import stand.lemonade.service.ShiftService;
import stand.lemonade.util.LemonadeValidator;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("api/payment")
public class IncomeResource {
	@Inject
	private IncomeService incomeService;

	@Inject
	private ShiftService shiftService;

	@Inject
	private LemonadeValidator validator;

	@GET
	public Response getAllIncome(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			List<IncomeModel> ret = incomeService.getAllIncome();
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/{shiftId}")
	public Response getIncomeByShiftId(@Auth User user, @PathParam("shiftId") LongParam shiftId)
			throws LemonadeException {

		if (Roles.isValidRole(user.getRole().toString())) {
			List<IncomeModel> ret = incomeService.getAllIncomeByShiftId(shiftId.get());
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	public Response createIncome(@Auth User user, IncomeModel incomeModel) throws LemonadeException {
		/* only waiter can create a new income */
		if (user.getRole().equals(Roles.WAITER)) {
			validator.beanValidation(incomeModel);

			incomeModel.setShiftId(shiftService.getShiftByUserId(user.getId()).getId());
			IncomeModel ret = incomeService.createIncome(incomeModel);
			return Response.ok().entity(ret).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}
}
