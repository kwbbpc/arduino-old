package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class LightSensorParameters implements ISensorParameters
{

	private int lightLevel;

	public LightSensorParameters()
	{
		this.lightLevel = Integer.MAX_VALUE;
	}

	/**
	 * Gets the light level in luminosity
	 * 
	 * @return
	 */
	public int getLightLevel()
	{
		return lightLevel;
	}

	/**
	 * Sets the light level in luminosity
	 * 
	 * @param lightLevel
	 */
	public void setLightLevel(int lightLevel)
	{
		this.lightLevel = lightLevel;
	}

}
