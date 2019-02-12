package has.core.page.exceptions;

public class InvalidLoginException extends PageProcessingException
{
	public InvalidLoginException()
	{
		super("Invalid Login Information");
	}
}
