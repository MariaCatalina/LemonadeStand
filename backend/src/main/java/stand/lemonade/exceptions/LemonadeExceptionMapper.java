package stand.lemonade.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.StatusType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class LemonadeExceptionMapper implements ExceptionMapper<Exception> {

	@Override
	public Response toResponse(Exception exception) {

		Response.Status responseStatus = Response.Status.INTERNAL_SERVER_ERROR;

		if (exception instanceof LemonadeException) {
			LemonadeException lemonadeEx = (LemonadeException) exception;

			switch (lemonadeEx.errorCode) {

			case UNAUTHORIZED:
				responseStatus = Response.Status.UNAUTHORIZED;
				break;
			case WRONG_CREDENTIALS:
				responseStatus = Response.Status.FORBIDDEN;
				break;

			case BEAN_VALIDATION_ERROR:
			case GENERIC_ENTITY_NOT_FOUND_ERROR:
			case GENERIC_ENTITY_DUPLICATE_FOUND_ERROR:
			case NOT_AVAILABLE:
			case INVALID_INPUT_MODEL:
			case MALFORMED_QUERY_STRING:
			case DELETED_ENTITY:
			case INVALID_EMAIL:
			case WALLET_NOT_FOUND:
			case SHIFT_NOT_FOUND:
			case USER_NOT_FOUND:
			case EXPENSE_NOT_FOUND:
			case PAYMENT_NOT_FOUND:
			case WALLET_TAKEN:
				responseStatus = Response.Status.BAD_REQUEST;
				break;

			case MISSING_PERMISSION:
				responseStatus = Response.Status.FORBIDDEN;
				break;

			case GENERIC_ERROR:
			case DB_ERROR:
			case PARSE_ERROR:
			default:
				responseStatus = Response.Status.INTERNAL_SERVER_ERROR;
				break;
			}

			return Response.status(responseStatus).entity(lemonadeEx.errorCode.getErrorMessage()).build();

		}
		
		if (exception instanceof WebApplicationException){
			StatusType statusInfo = ((WebApplicationException) exception).getResponse().getStatusInfo();
			responseStatus=Response.Status.fromStatusCode(statusInfo.getStatusCode());
			return Response.status(responseStatus).entity(ErrorCodes.GENERIC_ERROR.getErrorMessage()).build();
					
		}
		
		return Response.status(responseStatus).entity(ErrorCodes.GENERIC_ERROR.getErrorMessage()).build();
		
		// for DEVELOPMENT mode only. In production comment the code below and
		// uncomment lines 51-52
		// StringWriter sw = new StringWriter();
		// PrintWriter pw = new PrintWriter(sw);
		// exception.printStackTrace(pw);
		// return Response.status(responseStatus).entity(sw.toString()).build();
	}

}