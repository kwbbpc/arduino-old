package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.CarbonMonoxideParameters;

import java.util.List;

public class CarbonMonoxideParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{
		CarbonMonoxideParameters parameters = new CarbonMonoxideParameters();

		if (data.size() > 0)
		{
			parameters.setCoPpm(Double.parseDouble(data.get(0)));
		}

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		CarbonMonoxideParameters unitParameters = (CarbonMonoxideParameters) parameters;

		String message = Double.toString(unitParameters.getCoPpm());

		return message;
	}

}
