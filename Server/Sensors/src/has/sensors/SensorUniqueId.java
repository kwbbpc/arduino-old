package has.sensors;

import has.sensors.core.TypeSensor;

/**
 * @author KBroadway unique id construct of a sensor. consists of the
 *         arduino id (platform) + the sensor id
 */
public class SensorUniqueId
{
	private TypeSensor sensorId;
	private String platformId;

	public SensorUniqueId(String platformId, TypeSensor sensorId)
	{
		this.sensorId = sensorId;
		this.platformId = platformId;
	}


	public TypeSensor getSensorId()
	{
		return sensorId;
	}

	public String getPlatformId()
	{
		return platformId;
	}

	public boolean equals(SensorUniqueId comparable)
	{
		return (this.sensorId == comparable.sensorId) && (this.platformId == comparable.platformId);
	}

	@Override
	public String toString()
	{
		return platformId + sensorId.toString();
	}
}