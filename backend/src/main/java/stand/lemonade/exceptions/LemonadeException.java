package stand.lemonade.exceptions;

@SuppressWarnings("serial")
public class LemonadeException extends Exception {

	public ErrorCodes errorCode;

	public LemonadeException() {
	}

	public LemonadeException(ErrorCodes errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public ErrorCodes getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(ErrorCodes errorCode) {
		this.errorCode = errorCode;
	}

}
