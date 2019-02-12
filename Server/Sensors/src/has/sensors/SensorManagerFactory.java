package has.sensors;

import has.arduino.Arduino;
import has.communications.ISerialBaseStation;
import has.sensors.impl.SensorManagerImpl;

public class SensorManagerFactory
{

	private static ISensorManager manager;
	private static ISerialBaseStation arduino;

	/**
	 * This creates the sensor manager for the caller to use. ideally, an enum
	 * would get passed in and the base station is created based on the type
	 * passed in, but currently the only basestation is the arduino, so it's
	 * hardcoded.
	 * 
	 * Easy enough to change later.
	 * 
	 * @return ISensorManager
	 */
	public static ISensorManager createSensorManager()
	{
		if (manager == null)
		{

			if (arduino == null)
			{
				// set up the USB basestation device
				arduino = new Arduino();
				arduino.initializeDevice();
			}

			return new SensorManagerImpl(arduino);
		} else
		{
			return manager;
		}
	}
}
