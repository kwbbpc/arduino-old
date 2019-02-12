package has.sensors.impl;

import has.communications.ISerialBaseStation;
import has.parsing.HasMessenger;
import has.parsing.MessageId;
import has.sensors.Control;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.UnitCollection;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.streaming.StreamManager;
import has.streaming.impl.StreamManagerImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author KBroadway
 * 
 *         Implementation of the sensor manager
 * 
 */
public class SensorManagerImpl implements ISensorManager
{

	private UnitCollection sensorMap;
	private HasMessenger messenger;
	private ISerialBaseStation baseStation;
	private final StreamManager streamManager;

	public SensorManagerImpl(ISerialBaseStation usbDevice)
	{
		sensorMap = new UnitCollection();
		baseStation = usbDevice;
		messenger = new HasMessenger();
		baseStation.connectSerialPortListener(this);
		this.streamManager = new StreamManagerImpl();

	}

	@Override
	public List<HasUnit> find(TypeSensor type)
	{

		List<HasUnit> sensorListByType = new ArrayList<HasUnit>();

		for (HasUnit unit : sensorMap.values())
			if (unit.getTypeSensor() == type)
				sensorListByType.add(unit);

		return sensorListByType;

	}

	@Override
	public HasUnit find(String location, TypeSensor type)
	{
		// create the unique Id from the location and id
		SensorUniqueId sensorId = new SensorUniqueId(location, type);
		HasUnit unit = sensorMap.get(sensorId.toString());

		if (unit == null)
			unit = new SensorNull();

		return unit;
	}

	@Override
	public HasUnit find(String uniqueIdStr)
	{
		// create the unique Id from the location and id
		HasUnit unit = sensorMap.get(uniqueIdStr);

		if (unit == null)
			unit = new SensorNull();

		return unit;
	}

	@Override
	public void updateSensor(String location, TypeSensor sensorTypeId,
			ISensorParameters parameters)
	{
		// create the sensor's Id.
		SensorUniqueId sensorUpdateId = new SensorUniqueId(location,
				sensorTypeId);

		// find the sensor in the map
		HasUnit unitToUpdate = sensorMap.get(sensorUpdateId.toString());

		// if not found, this is a new sensor to add
		if (unitToUpdate == null)
		{

			sensorMap.put(sensorUpdateId.toString(), SensorFactory
					.createSensor(this, sensorTypeId, location, parameters));
		}
		// if it is found, update that sensor's data.
		else
		{
			try
			{
				Sensor sensor = (Sensor) unitToUpdate;
				sensor.update(parameters);
			} catch (ClassCastException e)
			{
				// trying to update a non-sensor unit!
				assert (false);
			}

		}

	}

	@Override
	public void serialPortReadEvent(String message)
	{
		// Tell the parser to parse the message and update with the
		// ISensorUpdaterCallback callback.
		messenger.ParseMessage(message, this);
	}

	@Override
	public void SendMessageToDevice(MessageId.Type messageType,
			String location, TypeSensor type, ISensorParameters parameters)
	{

		HasUnit sensorToSend = find(location, type);

		try
		{
			Control control = (Control) sensorToSend;

			// List<String> convertedData = control.convertData(data);
			String messageToSend = messenger.FormatAndSendMessage(messageType,
					location, type, parameters);

			System.out.println("Sending message [" + messageToSend + "]");

			baseStation.send(messageToSend);
		} catch (ClassCastException e)
		{
			// trying to send data to a non-control unit!
			assert (false);
		}
	}

	@Override
	public UnitCollection getUnitCollection()
	{
		return sensorMap;
	}

	@Override
	public void DiscoverSensors(boolean clearSensorList)
	{
		if (clearSensorList)
			sensorMap.clear();

		messenger.FormatAndSendMessage(MessageId.Type.DISCOVER, null,
				TypeSensor.INVALID, null);
	}

	@Override
	public Set<String> getRoomSet()
	{
		Set<String> roomSet = new HashSet<String>();
		// get all the rooms into a set
		for (HasUnit unit : this.sensorMap.values())
		{
			if (unit.getRoomLocation() != null)
				roomSet.add(unit.getRoomLocation());
		}

		return roomSet;
	}

	public StreamManager getStreamManager()
	{
		return streamManager;
	}

}
