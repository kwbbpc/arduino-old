package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.LightSensorParameters;

/**
 * Implementation of a generic data sensor that has only a single peice of
 * information.
 * 
 */
public class SensorLight extends HasUnit implements Sensor
{

	private LightSensorParameters parameters;

	public SensorLight(String locationId, TypeSensor sensorType,
			LightSensorParameters parameters)
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

		LightSensorParameters newParameters = (LightSensorParameters) parameters;

		if (newParameters.getLightLevel() != Integer.MAX_VALUE)
			this.parameters.setLightLevel(newParameters.getLightLevel());

	}

}
