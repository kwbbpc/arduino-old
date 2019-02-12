package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

import java.util.HashMap;
import java.util.Map;

public class ThermostatParameters implements ISensorParameters
{

	private Map<String, Integer> roomThermostatSettings;

	private int averageSetting;

	public ThermostatParameters()
	{
		roomThermostatSettings = new HashMap<String, Integer>();
		averageSetting = Integer.MAX_VALUE;
	}

	public Map<String, Integer> getRoomThermostatSettings()
	{
		return roomThermostatSettings;
	}

	public void setRoomThermostatSettings(
			Map<String, Integer> roomThermostatSettings)
	{
		this.roomThermostatSettings = roomThermostatSettings;
	}

	public int getAverageSetting()
	{
		return averageSetting;
	}

	public void setAverageSetting(int averageSetting)
	{
		this.averageSetting = averageSetting;
	}

}
