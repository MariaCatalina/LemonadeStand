package stand.lemonade.exceptions;

public class ErrorMessage {

	public Integer code;
	public String message;

	public ErrorMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
