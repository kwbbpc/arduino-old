package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class TemperatureParameters implements ISensorParameters
{

	private int temperature;

	public TemperatureParameters()
	{
		temperature = Integer.MAX_VALUE;
	}

	/**
	 * Gets the temperature
	 * 
	 * @return
	 */
	public int getTemperature()
	{
		return temperature;
	}

	/**
	 * Sets the temperature
	 * 
	 * @param temperature
	 */
	public void setTemperature(int temperature)
	{
		this.temperature = temperature;
	}

}
