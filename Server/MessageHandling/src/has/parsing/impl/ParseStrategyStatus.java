package has.parsing.impl;

import has.parsing.IParseStrategy;
import has.parsing.units.UnitParser;
import has.parsing.units.UnitParserFactory;
import has.sensors.core.ISensorParameters;
import has.sensors.core.ISensorUpdaterCallback;
import has.sensors.core.TypeSensor;

import java.util.List;

public class ParseStrategyStatus implements IParseStrategy
{

	@Override
	public void process(List<String> messageParameters,
			ISensorUpdaterCallback updater)
	{
		// make sure the message is long enough
		if (messageParameters.size() < 3)
			return;

		// save the platform location
		String location = messageParameters.remove(0);

		// save the sensor type
		String sensorId = messageParameters.remove(0);
		TypeSensor sensorTypeId = TypeSensor.lookup(Integer.parseInt(sensorId));

		// save the parameters
		UnitParser parser = UnitParserFactory.getMessageParser(sensorTypeId);
		ISensorParameters parameters = parser.parse(messageParameters);

		// call the updater with the info.
		updater.updateSensor(location, sensorTypeId, parameters);

	}
}
