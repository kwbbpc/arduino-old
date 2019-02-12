package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.LightSensorParameters;
import has.sensors.parameters.WebcamParameters;

import java.util.List;

public class WebcamParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{

		int degreesRotation = Integer.MAX_VALUE;

		if (data.size() > 0)
			degreesRotation = Integer.parseInt(data.get(0));

		// TODO: dynamic mapping of webcams attached to the system
		WebcamParameters parameters = new WebcamParameters(degreesRotation);

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
