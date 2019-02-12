package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class HumidityParameters implements ISensorParameters
{

	private double humidity;

	/**
	 * Gets the humidity in percentage
	 * 
	 * @return
	 */
	public double getHumidity()
	{
		return humidity;
	}

	/**
	 * Sets the humidity percentage
	 * 
	 * @param humidity
	 */
	public void setHumidity(double humidity)
	{
		this.humidity = humidity;
	}

}
