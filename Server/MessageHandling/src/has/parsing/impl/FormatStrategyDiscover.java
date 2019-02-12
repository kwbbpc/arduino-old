package has.parsing.impl;

import has.parsing.HasMessenger;
import has.parsing.IFormatStrategy;
import has.parsing.MessageId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;

/**
 * @Describe Formats and sends a discover message to all arduinos
 */
public class FormatStrategyDiscover implements IFormatStrategy
{

	@Override
	public String Format(String location, TypeSensor type,
			ISensorParameters parameters)
	{
		String message = HasMessenger.signature + ","
				+ MessageId.Type.DISCOVER.toId() + "," + "0"; // null location
																// id

		return message;
	}

}
