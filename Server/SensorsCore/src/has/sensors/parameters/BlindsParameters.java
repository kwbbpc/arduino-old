package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class BlindsParameters implements ISensorParameters
{

	private int degreesRotation;

	public BlindsParameters()
	{
		degreesRotation = Integer.MAX_VALUE;
	}

	/**
	 * Gets the degrees of rotation of the blinds
	 * 
	 * @return
	 */
	public int getDegreesRotation()
	{
		return degreesRotation;
	}

	/**
	 * Sets the degrees Rotation of the blinds.
	 * 
	 * @param degreesRotation
	 */
	public void setDegreesRotation(int degreesRotation)
	{
		this.degreesRotation = degreesRotation;
	}

}
