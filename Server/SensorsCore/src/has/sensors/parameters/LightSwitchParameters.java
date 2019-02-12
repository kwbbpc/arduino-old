package has.sensors.parameters;

import has.sensors.core.ISensorParameters;
import has.sensors.state.LightSwitchState;

public class LightSwitchParameters implements ISensorParameters
{

	private LightSwitchState state;

	public LightSwitchParameters()
	{
		state = LightSwitchState.INVALID;
	}

	/**
	 * Gets the light switch state
	 * 
	 * @return
	 */
	public LightSwitchState getState()
	{
		return state;
	}

	/**
	 * Sets the light switch state
	 * 
	 * @param state
	 */
	public void setState(LightSwitchState state)
	{
		this.state = state;
	}

}
