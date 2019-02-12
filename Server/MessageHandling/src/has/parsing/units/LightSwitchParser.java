package has.parsing.units;

import has.sensors.core.ISensorParameters;
import has.sensors.parameters.LightSwitchParameters;
import has.sensors.state.LightSwitchState;

import java.util.List;

public class LightSwitchParser implements UnitParser
{

	@Override
	public ISensorParameters parse(List<String> data)
	{

		LightSwitchParameters parameters = new LightSwitchParameters();

		if (data.size() > 0)
			parameters.setState(LightSwitchState.fromString(data.get(0)));

		return parameters;
	}

	@Override
	public String formatData(ISensorParameters parameters)
	{

		LightSwitchParameters unitParameters = (LightSwitchParameters) parameters;

		String message = unitParameters.getState().toString();

		return message;
	}

}
