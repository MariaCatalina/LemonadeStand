package stand.lemonade.exceptions;

public enum ErrorCodes {

	GENERIC_ERROR(1, "Generic error"),

	DB_ERROR(2, "Persistence error"),

	UNAUTHORIZED(10, "Not logged in."),

	WRONG_CREDENTIALS(11, "Incorrect credentials."),

	MISSING_PERMISSION(20, "Missing permission."),

	BEAN_VALIDATION_ERROR(30, "Input model is invalid."),

	MALFORMED_QUERY_STRING(31, "Malformed query string."),

	INVALID_INPUT_MODEL(32, "Cannot map request body to expected model."),

	INVALID_EMAIL(33, "Email is already in the data base"),
	
	PARSE_ERROR(34, "Parsing Date Exception"),

	GENERIC_ENTITY_NOT_FOUND_ERROR(40, "Entity does not exist"),

	GENERIC_ENTITY_DUPLICATE_FOUND_ERROR(41, "Entity has duplicates."),

	NOT_AVAILABLE(42, "Entity is not available"),
	
	WALLET_NOT_FOUND(43, "Wallet not found"),
	
	WALLET_TAKEN(48, "Wallet has been taken"),
	
	SHIFT_NOT_FOUND(44, "Shift not found"),
	
	USER_NOT_FOUND(45, "User not found"),
	
	EXPENSE_NOT_FOUND(46, "Expense not found"),
	
	PAYMENT_NOT_FOUND(47, "Payment not found"),

	DELETED_ENTITY(50, "Entity in not active"),
	
	NOT_FOUND(60,"URL not found");

	private ErrorMessage errorMessage;

	private ErrorCodes(Integer code, String message) {
		this.errorMessage = new ErrorMessage(code, message);
	}

	public ErrorMessage getErrorMessage() {
		return errorMessage;
	}

}
