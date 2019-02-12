package has.sensors.state;

public enum BrightnessState implements SensorState
{
	BRIGHT, DIM, DARK;

	public static BrightnessState convertToBrightness(int lightLevel)
	{
		if (lightLevel < 500)
			return BrightnessState.DARK;
		if (lightLevel < 750)
			return BrightnessState.DIM;
		else
			return BrightnessState.BRIGHT;
	}

	public String toString()
	{
		switch (this)
		{
		case BRIGHT:
			return "Bright";
		case DIM:
			return "Dim";
		case DARK:
			return "Dark";
		}

		return null;
	}
}