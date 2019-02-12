package has.sensors.impl;

import has.parsing.MessageId.Type;
import has.sensors.Control;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.LightSwitchParameters;

public class SensorLightSwitch extends HasUnit implements Sensor, Control
{

	private LightSwitchParameters parameters;
	private ISensorManager manager;

	/**
	 * Constructor.
	 * 
	 * @param platformId
	 *            the remote platform ID this unit is located on
	 * @param sensorType
	 *            the type of sensor
	 * @param parameters
	 *            the parameters this sensor has for data/control
	 * @param sensorManager
	 *            a reference to the main sensor manager
	 */
	public SensorLightSwitch(String platformId, TypeSensor sensorType,
			LightSwitchParameters parameters, ISensorManager sensorManager)
	{
		super(new SensorUniqueId(platformId, sensorType));

		this.parameters = parameters;
		this.manager = sensorManager;

	}

	@Override
	public void sendData(ISensorParameters parametersToSend)
	{
		this.manager.SendMessageToDevice(Type.ACTION, this.getPlatformId(),
				this.getTypeSensor(), parametersToSend);

	}

	@Override
	public ISensorParameters getParameters()
	{
		return parameters;
	}

	@Override
	public void update(ISensorParameters parameters)
	{
		LightSwitchParameters newParameters = (LightSwitchParameters) parameters;

		this.parameters.setState(newParameters.getState());

	}
}
