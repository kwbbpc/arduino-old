package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.BlindsParameters;

import java.util.List;

public class BlindsParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{

		BlindsParameters parameters = new BlindsParameters();

		if (data.size() > 0)
		{

			final int degreesRotationIndex = 0;
			parameters.setDegreesRotation(Integer.parseInt(data
					.get(degreesRotationIndex)));
		}

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		BlindsParameters unitParameters = (BlindsParameters) parameters;

		String message = Integer.toString(unitParameters.getDegreesRotation());

		return message;
	}

}
