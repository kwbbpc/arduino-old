package has.sensors.core;


public interface ISensorUpdaterCallback
{

	/**
	 * Callback function definition for updating sensors.
	 * 
	 * @param location
	 *            the location id of the sensor
	 * @param sensorTypeId
	 *            the sensor type
	 * @param data
	 *            new data to be provided to the sensor
	 */
	public void updateSensor(String location, TypeSensor sensorTypeId,
			ISensorParameters params);

}
