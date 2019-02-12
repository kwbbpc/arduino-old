package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.TemperatureParameters;

import java.util.List;

public class TemperatureParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{
		TemperatureParameters parameters = new TemperatureParameters();

		if (data.size() > 0)
			parameters.setTemperature(Integer.parseInt(data.get(0)));

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		TemperatureParameters unitParameters = (TemperatureParameters) parameters;

		String message = Integer.toString(unitParameters.getTemperature());

		return message;
	}

}
