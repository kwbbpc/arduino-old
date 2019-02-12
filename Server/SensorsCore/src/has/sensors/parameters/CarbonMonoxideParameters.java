package has.sensors.parameters;

import has.sensors.core.ISensorParameters;

public class CarbonMonoxideParameters implements ISensorParameters
{
	private double coPpm;

	public CarbonMonoxideParameters()
	{

		coPpm = Double.MAX_VALUE;
	}

	/**
	 * Gets the carbon monoxide levels in PPM
	 * 
	 * @return
	 */
	public double getCoPpm()
	{
		return coPpm;
	}

	/**
	 * Sets the carbon monoxide levels in PPM
	 * 
	 * @param coPpm
	 */
	public void setCoPpm(double coPpm)
	{
		this.coPpm = coPpm;
	}
}
