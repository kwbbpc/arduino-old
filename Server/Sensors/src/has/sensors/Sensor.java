package has.sensors;

import has.sensors.core.ISensorParameters;

/**
 * 
 * Common interface for all types of sensors that report a status
 * 
 */
public interface Sensor
{

	/**
	 * @return String the readable data that the sensor is portraying
	 */
	public ISensorParameters getParameters();

	/**
	 * Updates a sensor with new data
	 * 
	 * @param data
	 *            the string list of data to update a sensor with
	 */
	public void update(ISensorParameters parameters);

}
