package has.parsing;

import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;

public interface IFormatStrategy
{

	/**
	 * Formats a string message to be sent
	 * 
	 * @param location
	 *            location string
	 * @param type
	 *            sensor type
	 * @param data
	 *            the array of data
	 * @return String the formatted message comma delineated, ready to be sent.
	 */
	public String Format(String location, TypeSensor type,
			ISensorParameters parameters);

}
