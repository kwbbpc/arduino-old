package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class WebcamParameters implements ISensorParameters
{

	private final int degreesRotation;

	public WebcamParameters(int degreesRotation)
	{

		this.degreesRotation = degreesRotation;

	}

	public int getDegreesRotation()
	{
		return degreesRotation;
	}

}
