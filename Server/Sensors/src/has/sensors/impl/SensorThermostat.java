package has.sensors.impl;

import has.parsing.MessageId.Type;
import has.sensors.Control;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.sensors.Sensor;
import has.sensors.SensorUniqueId;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.ThermostatParameters;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SensorThermostat extends HasUnit implements Sensor, Control
{

	private ThermostatParameters parameters;
	private ISensorManager manager;

	public SensorThermostat(String locationId, TypeSensor sensorType,
			ThermostatParameters parameters, ISensorManager sensorManager)
	{

		super(new SensorUniqueId(locationId, sensorType));

		this.parameters = parameters;

		this.manager = sensorManager;

	}

	public int getAverageTemperature()
	{

		int averageTemp = 0;

		List<HasUnit> temperatureSensors = manager.getUnitCollection()
				.getByType(TypeSensor.TEMPERATURE);

		for (HasUnit unit : temperatureSensors)
		{
			SensorTemperature tempSensor = (SensorTemperature) unit;

			averageTemp += tempSensor.getTemperature();

		}

		return averageTemp / temperatureSensors.size();

	}

	public Set<String> getManagedRooms()
	{
		List<HasUnit> temperatureSensors = manager.getUnitCollection()
				.getByType(TypeSensor.TEMPERATURE);

		Set<String> rooms = new HashSet<String>();

		for (HasUnit unit : temperatureSensors)
		{
			if (unit.getRoomLocation() != null)
				if (unit.getRoomLocation() != "")
					rooms.add(unit.getRoomLocation());

		}

		return rooms;

	}

	public int getRoomTemperature(String roomName)
	{
		List<HasUnit> temperatureSensors = manager.getUnitCollection()
				.getByType(TypeSensor.TEMPERATURE);

		for (HasUnit unit : temperatureSensors)
		{
			if (unit.getRoomLocation().equals(roomName))
			{

				SensorTemperature tempSensor = (SensorTemperature) unit;

				return tempSensor.getTemperature();

			}

		}

		return Integer.MAX_VALUE;

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

		ThermostatParameters thermostatSettings = (ThermostatParameters) parameters;

		if (thermostatSettings.getAverageSetting() != Integer.MAX_VALUE)
		{
			this.parameters.setAverageSetting(thermostatSettings
					.getAverageSetting());
		}

		// Update the room settings for all rooms in the saved parameters
		Map<String, Integer> roomUpdateSettings = thermostatSettings
				.getRoomThermostatSettings();

		for (String room : roomUpdateSettings.keySet())
		{

			// update the room's temp setting.
			this.parameters.getRoomThermostatSettings().put(room,
					roomUpdateSettings.get(room));

		}

	}

}
