package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.TemperatureParameters;

/**
 * Implementation of a generic data sensor that has only a single peice of
 * information.
 * 
 */
public class SensorTemperature extends HasUnit implements Sensor
{

	private TemperatureParameters parameters;

	public SensorTemperature(String locationId, TypeSensor sensorType,
			TemperatureParameters parameters)
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

		TemperatureParameters newParameters = (TemperatureParameters) parameters;

		if (newParameters.getTemperature() != Double.MAX_VALUE)
			this.parameters.setTemperature(newParameters.getTemperature());

	}

	public int getTemperature()
	{
		return parameters.getTemperature();
	}

}
