package stand.lemonade.resources;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.EndShiftModel;
import stand.lemonade.models.ShiftModel;
import stand.lemonade.models.StartShiftModel;
import stand.lemonade.service.ShiftService;
import stand.lemonade.util.LemonadeValidator;

@Path("/api/shift")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ShiftResource {

	@Inject
	private ShiftService shiftService;

	@Inject
	private LemonadeValidator validator;
	
	@GET
	@Path("/start")
	public Response getShiftIdByUserId(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			ShiftModel model = shiftService.getShiftByUserId(user.getId());

			return Response.status(200).entity(model).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	@Path("/start")
	public Response startShift(@Auth User user, StartShiftModel model) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			model.setUserId(user.getId());
			
			validator.beanValidation(model);

			StartShiftModel startShiftModel = shiftService.addShift(model);

			return Response.status(201).entity(startShiftModel).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@GET
	@Path("/end")
	public Response getIncomeAndCostsData(@Auth User user) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			EndShiftModel model = shiftService.getShiftData(shiftService.getShiftByUserId(user.getId()).getId());

			return Response.status(200).entity(model).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@PUT
	@Path("/end")
	public Response endShift(@Auth User user, EndShiftModel model) throws LemonadeException {
		if (Roles.isValidRole(user.getRole().toString())) {
			validator.beanValidation(model);

			model.setShiftId(shiftService.getShiftByUserId(user.getId()).getId());
			EndShiftModel endShiftModel = shiftService.endShift(model);

			return Response.ok().entity(endShiftModel).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

}