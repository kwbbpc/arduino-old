package has.sensors.state;

public enum BlindState implements SensorState
{
	CLOSED_DOWN, OPEN_90, CLOSED_UP;

	private int degrees;

	public int getDegrees()
	{
		return degrees;
	}

	public void setDegrees(int degrees)
	{
		this.degrees = degrees;
	}

	public String toString()
	{
		switch (this)
		{
		case CLOSED_DOWN:
			return "Closed, down";
		case OPEN_90:
			return "Open";
		case CLOSED_UP:
			return "Closed, up";
		default:
			return null;
		}
	}

	public static BlindState getState(int degrees)
	{

		if (degrees < 30)
			return BlindState.CLOSED_UP;
		else if (degrees > 135)
			return BlindState.CLOSED_DOWN;
		else
			return BlindState.OPEN_90;

	}

	public static BlindState convertToState(String state)
	{

		if (state.equals("up") || state.equals("Closed, up"))
			return BlindState.CLOSED_UP;
		if (state.equals("down") || state.equals("Closed, down"))
			return BlindState.CLOSED_DOWN;
		if (state.equals("open") || state.equals("Open"))
			return BlindState.OPEN_90;

		try
		{
			int degrees = Integer.parseInt(state);
			return getState(degrees);
		} catch (NumberFormatException e)
		{
		}

		return null;

	}
}