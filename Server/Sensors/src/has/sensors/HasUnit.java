package has.sensors;

import has.sensors.core.TypeSensor;

/**
 * A unit that HAS can interact with. Typically this will be either a control or
 * a sensor. Common functions that HAS expects are included here.
 * 
 */
public abstract class HasUnit
{

	private SensorUniqueId uniqueId;
	private String displayName;
	private String roomLocation;

	protected HasUnit(SensorUniqueId uniqueId)
	{

		this.uniqueId = uniqueId;
		this.displayName = uniqueId.getSensorId().toString() + " ("
				+ uniqueId.getPlatformId() + ")";
		this.roomLocation = null;

	}

	/**
	 * @return String return the location id of the sensor. usually this is the
	 *         id of the board that the sensor is attached to.
	 * 
	 */
	public final String getPlatformId()
	{
		return this.uniqueId.getPlatformId();
	}

	/**
	 * @return String returns the readable name of the sensor.
	 * 
	 */
	public final String getDisplayName()
	{
		return this.displayName;
	}

	/**
	 * 
	 * sets the name of a sensor
	 * 
	 */
	public final void setDisplayName(String newName)
	{
		this.displayName = newName;
	}

	/**
	 * @return TypeSensor returns the enum type of the sensor.
	 * 
	 */
	public final TypeSensor getTypeSensor()
	{
		return this.getUniqueId().getSensorId();
	}

	/**
	 * @return String returns the platformId + sensorId. This unique id is used
	 *         to detect new sensors when a status message comes up with a new
	 *         unique id.
	 * 
	 */
	public final SensorUniqueId getUniqueId()
	{
		return this.uniqueId;
	}

	/**
	 * Gets the user specified room location of this sensor
	 * 
	 * @return the room location
	 */
	public final String getRoomLocation()
	{
		return this.roomLocation;
	}

	/**
	 * Sets the user specified room location of this sensor
	 * 
	 * @return the room location
	 */
	public final void setRoomLocation(String roomLocation)
	{
		this.roomLocation = roomLocation;
	}

}
