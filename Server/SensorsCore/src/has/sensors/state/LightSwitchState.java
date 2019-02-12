package has.sensors.state;

public enum LightSwitchState implements SensorState
{
	OFF("0"), ON("1"), INVALID("0xFF");

	private String str;

	LightSwitchState(String str)
	{
		this.str = str;
	}

	public String toString()
	{
		return str;
	}

	public static LightSwitchState fromString(String str)
	{
		if (str.equals("1"))
			return ON;
		if (str.equals("0"))
			return OFF;

		return INVALID;
	}
}