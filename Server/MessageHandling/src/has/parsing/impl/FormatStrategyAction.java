package has.parsing.impl;

import has.parsing.HasMessenger;
import has.parsing.IFormatStrategy;
import has.parsing.MessageId;
import has.parsing.units.UnitParserFactory;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;

public class FormatStrategyAction implements IFormatStrategy
{

	@Override
	public String Format(String location, TypeSensor type,
			ISensorParameters parameters)
	{
		//@formatter:off
		
		//build the header
		String message =  HasMessenger.signature + 
							"," + MessageId.Type.ACTION.toId() + 
							"," + location + 
							"," + type.toId() +
							"," + UnitParserFactory.getMessageParser(type).formatData(parameters);
		
		//@formatter:on

		// tack on the data

		// tack on a \r\n
		message = message + "\r\n";

		return message;
	}
}
