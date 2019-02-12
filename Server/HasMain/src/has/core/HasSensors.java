package has.core;

import has.sensors.ISensorManager;
import has.sensors.SensorManagerFactory;

public class HasSensors
{

	// TODO: get rid of this class, it seems to just be an extra layer.
	private ISensorManager sensorManager;

	public HasSensors()
	{

		sensorManager = SensorManagerFactory.createSensorManager();
	}

	public ISensorManager getSensorManager()
	{
		return this.sensorManager;
	}

}
