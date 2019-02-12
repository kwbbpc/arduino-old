package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.NullParameters;

public class SensorNull extends HasUnit implements Sensor
{

	public SensorNull()
	{
		super(new SensorUniqueId("", TypeSensor.INVALID));
	}

	@Override
	public ISensorParameters getParameters()
	{
		return new NullParameters();
	}

	@Override
	public void update(ISensorParameters parameters)
	{
		// do nothing

	}

}
