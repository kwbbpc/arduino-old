package has.core.page.exceptions;

public class PageProcessingException extends Exception
{

	String message;

	public PageProcessingException(String message)
	{
		super();
		this.message = message;
	}

}
