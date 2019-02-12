package has.sensors;

import has.communications.ISerialPortListener;
import has.parsing.MessageId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.ISensorUpdaterCallback;
import has.sensors.core.TypeSensor;
import has.streaming.StreamManager;

import java.util.List;
import java.util.Set;

public interface ISensorManager extends ISerialPortListener,
		ISensorUpdaterCallback
{

	/**
	 * @return a collection of all the sensors
	 */
	public UnitCollection getUnitCollection();

	/**
	 * Retrieves all sensors of a certain type
	 * 
	 * @param sensor
	 *            the type of sensor
	 * @return List<ISensor> all of the sensors of a specific type that are
	 *         reporting.
	 * 
	 * 
	 */
	public List<HasUnit> find(TypeSensor sensor);

	/**
	 * Retrieves a sensor by id
	 * 
	 * @param location
	 *            the location of the sensor
	 * @param type
	 *            the type of sensor
	 * @return ISensor this is the sensor you are looking for
	 */
	public HasUnit find(String location, TypeSensor type);

	/**
	 * 
	 * Retrieves a sensor by unique id unique ID is the location + type
	 * 
	 * @param String
	 *            the uniqueID string representation of the sensor desired.
	 * @return This is the sensor you're looking for.
	 */
	public HasUnit find(String uniqueIdStr);

	/**
	 * 
	 * Update a sensor's data.
	 * 
	 * @param location
	 *            the location id of the sensor
	 * @param type
	 *            the sensor type
	 * @param data
	 *            the data list to pass to the sensor for updating.
	 */
	@Override
	public void updateSensor(String location, TypeSensor type,
			ISensorParameters params);

	/**
	 * Sends an action message to the connected device.
	 * 
	 * @param location
	 *            the location of the sensor to send information to
	 * @param type
	 *            the type of sensor to send information to
	 * @param message
	 *            the data to send the sensor
	 */
	public void SendMessageToDevice(MessageId.Type messageType,
			String location, TypeSensor type, ISensorParameters params);

	/**
	 * Sends a discovery message to all base stations, indicating they should
	 * start sending their status messages of all attached sensors.
	 */
	public void DiscoverSensors(boolean clearSensorList);

	public Set<String> getRoomSet();

	public StreamManager getStreamManager();

}
