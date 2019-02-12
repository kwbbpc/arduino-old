package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.HumidityParameters;

import java.util.List;

public class HumidityParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{

		HumidityParameters parameters = new HumidityParameters();

		if (data.size() > 0)
			parameters.setHumidity(Integer.parseInt(data.get(0)));

		return parameters;

	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		HumidityParameters unitParameters = (HumidityParameters) parameters;

		String message = Double.toString(unitParameters.getHumidity());

		return message;
	}
}
