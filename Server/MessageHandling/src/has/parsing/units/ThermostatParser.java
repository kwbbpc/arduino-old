package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.ThermostatParameters;

import java.util.List;

public class ThermostatParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{
		ThermostatParameters parameters = new ThermostatParameters();

		if (data.size() > 0)
			parameters.setAverageSetting(Integer.parseInt(data.get(0)));

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		// TODO: define and format the thermostat data
		return "";
	}

}
