package stand.lemonade.resources;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.dropwizard.auth.Auth;
import stand.lemonade.entities.Roles;
import stand.lemonade.entities.User;
import stand.lemonade.exceptions.ErrorCodes;
import stand.lemonade.exceptions.LemonadeException;
import stand.lemonade.models.HistoryIntervalModel;
import stand.lemonade.models.HistoryModel;
import stand.lemonade.models.HistoryWalletModel;
import stand.lemonade.service.HistoryService;
import stand.lemonade.util.LemonadeValidator;

@Path("/api/history")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoryResource {

	private static final String COMMA_DELIMITER = ",";
	private static final String NEW_LINE_SEPARATOR = " " + System.getProperty("line.separator") + " ";
	private static final String FILE_HEADER = "Wallet, Waiter, Start date, Finish date, Start sum, Charges, Expenses, Bank, Final Sum, Delta";

	@Inject
	private HistoryService historyService;

	@Inject
	private LemonadeValidator validator;

	@POST
	public Response getHistoryData(@Auth User user, HistoryIntervalModel model) throws LemonadeException {
		if (user.getRole().equals(Roles.ADMIN)) {
			validator.beanValidation(model);

			List<HistoryModel> modelCreated = historyService.getHistoryData(model);
			return Response.ok().entity(modelCreated).build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

	@POST
	@Path("/download")
	@Produces("text/csv")
	public Response csvFile(@Auth User user, HistoryIntervalModel model) throws IOException, LemonadeException {

		if (user.getRole().equals(Roles.ADMIN)) {
			validator.beanValidation(model);

			/* data from database */
			List<HistoryModel> modelCreated = historyService.getHistoryData(model);

			StringBuffer csvContent = new StringBuffer();
			csvContent.append(FILE_HEADER.toString());
			csvContent.append(NEW_LINE_SEPARATOR);

			/* create string with history data required */
			for (HistoryModel h : modelCreated) {
				List<HistoryWalletModel> hM = h.getData();

				for (HistoryWalletModel l : hM) {

					csvContent.append(h.getWalletName());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getWaiterName());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getStartShiftTime());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getEndShiftTime());
					csvContent.append(COMMA_DELIMITER);
					
					csvContent.append(l.getStartSum());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getIncomes());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getCosts());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getBankSum());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getFinalSum());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(l.getDelta());
					csvContent.append(COMMA_DELIMITER);

					csvContent.append(NEW_LINE_SEPARATOR);
				}
			}

			return Response.ok(csvContent).header("Content-Disposition", "attachment; filename=" + "history.csv")
					.build();
		} else {
			throw new LemonadeException(ErrorCodes.MISSING_PERMISSION);
		}
	}

}
