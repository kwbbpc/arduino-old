package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.LightSensorParameters;

import java.util.List;

public class LightSensorParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{
		LightSensorParameters parameters = new LightSensorParameters();

		if (data.size() > 0)
			parameters.setLightLevel(Integer.parseInt(data.get(0)));

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		LightSensorParameters unitParameters = (LightSensorParameters) parameters;

		String message = Integer.toString(unitParameters.getLightLevel());

		return message;
	}
}
