package has.sensors.impl;

import has.definitions.BrowserType;
import has.parsing.MessageId.Type;
import has.sensors.Control;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.WebcamParameters;
import has.streaming.StreamManager;

public class SensorWebcamServo extends HasUnit implements Sensor, Control
{

	private WebcamParameters parameters;
	private ISensorManager manager;
	private final StreamManager streamManager;

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
	public SensorWebcamServo(String platformId, TypeSensor sensorType,
			WebcamParameters parameters, ISensorManager sensorManager)
	{
		super(new SensorUniqueId(platformId, sensorType));

		this.parameters = parameters;
		this.manager = sensorManager;
		this.streamManager = sensorManager.getStreamManager();

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
		WebcamParameters newParameters = (WebcamParameters) parameters;

		this.parameters = newParameters;

	}

	/**
	 * Starts a stream for this webcam.
	 * 
	 * @param browserType
	 *            the browser requesting the stream
	 * @return the port the stream is available on
	 */
	public int startStream(BrowserType browserType)
	{

		return this.streamManager.startVideoStream(this.getUniqueId()
				.toString(), browserType);

	}

	public void endStream()
	{

		this.streamManager.endVideoStream(this.getUniqueId().toString());

	}
}
