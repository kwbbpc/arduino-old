package has.sensors.state;

public class ThermostatState implements SensorState
{

	// TODO: fill out this class to be a smarter thermostat

	private int tempSetting;

	ThermostatState()
	{

	}

	public void setTempSetting(int tempSetting)
	{
		this.tempSetting = tempSetting;
	}

	public int getTempSetting()
	{
		return this.tempSetting;
	}

}