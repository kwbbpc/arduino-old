package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.HumidityParameters;

/**
 * Implementation of a generic data sensor that has only a single peice of
 * information.
 * 
 */
public class SensorHumidity extends HasUnit implements Sensor
{

	private HumidityParameters parameters;

	public SensorHumidity(String locationId, TypeSensor sensorType,
			HumidityParameters parameters)
	{
		super(new SensorUniqueId(locationId, sensorType));
		this.parameters = parameters;
	}

	@Override
	public ISensorParameters getParameters()
	{
		return this.parameters;
	}

	@Override
	public void update(ISensorParameters parameters)
	{

		HumidityParameters newParameters = (HumidityParameters) parameters;

		if (newParameters.getHumidity() != Double.MAX_VALUE)
			this.parameters.setHumidity(newParameters.getHumidity());

	}

}
