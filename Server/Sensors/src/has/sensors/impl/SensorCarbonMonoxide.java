package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.CarbonMonoxideParameters;

/**
 * Implementation of a generic data sensor that has only a single peice of
 * information.
 * 
 */
public class SensorCarbonMonoxide extends HasUnit implements Sensor
{

	private CarbonMonoxideParameters parameters;

	public SensorCarbonMonoxide(String locationId, TypeSensor sensorType,
			CarbonMonoxideParameters parameters)
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

		CarbonMonoxideParameters newParameters = (CarbonMonoxideParameters) parameters;

		if (newParameters.getCoPpm() != Double.MAX_VALUE)
			this.parameters.setCoPpm(newParameters.getCoPpm());

	}

}
