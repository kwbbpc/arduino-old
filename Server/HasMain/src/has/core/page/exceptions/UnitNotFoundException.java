package has.core.page.exceptions;

public class UnitNotFoundException extends PageProcessingException
{

	public UnitNotFoundException(String uniqueId)
	{
		super("Sensor (" + uniqueId + ") could not be found in the system.");
	}

}
