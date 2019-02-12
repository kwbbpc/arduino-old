package has.user.exceptions;

public class InvalidUserSettingException extends Exception {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8807416455225808297L;
	private String message;
	
	public InvalidUserSettingException(String message) {
		super();
		this.message = message;
	}
	
	public String getMessage()
	{
		return this.message;
	}

}
