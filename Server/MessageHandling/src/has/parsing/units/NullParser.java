package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.NullParameters;

import java.util.List;

public class NullParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{

		return new NullParameters();

	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		return "";
	}
}
