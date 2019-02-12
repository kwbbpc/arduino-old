package has.parsing;

import has.sensors.core.ISensorUpdaterCallback;

import java.util.List;

public interface IParseStrategy
{

	/**
	 * Process a message and call an update function to update the corresponding
	 * sensor with new data.
	 * 
	 * @param messageParameters
	 *            the message parameters
	 * 
	 * @param updater
	 *            the callback function to call to update a sensor
	 */
	void process(List<String> messageParameters, ISensorUpdaterCallback updater);

}
