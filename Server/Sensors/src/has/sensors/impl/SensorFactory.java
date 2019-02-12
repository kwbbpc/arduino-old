package has.sensors.impl;

import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.sensors.core.ISensorParameters;
import has.sensors.core.TypeSensor;
import has.sensors.parameters.BlindsParameters;
import has.sensors.parameters.CarbonMonoxideParameters;
import has.sensors.parameters.HumidityParameters;
import has.sensors.parameters.LightSensorParameters;
import has.sensors.parameters.LightSwitchParameters;
import has.sensors.parameters.TemperatureParameters;
import has.sensors.parameters.ThermostatParameters;
import has.sensors.parameters.WebcamParameters;

public class SensorFactory
{

	public static HasUnit createSensor(ISensorManager manager, TypeSensor type,
			String platformId, ISensorParameters parameters)
	{
		switch (type)
		{

		// Generic control sensors with 1 peice of data and 1 action message
		case SERVO_BLINDS:
			return new SensorBlindsServo(platformId, type,
					(BlindsParameters) parameters, manager);
		case THERMOSTAT:
			return new SensorThermostat(platformId, type,
					(ThermostatParameters) parameters, manager);
		case TEMPERATURE:
			return new SensorTemperature(platformId, type,
					(TemperatureParameters) parameters);
		case LIGHT:
			return new SensorLight(platformId, type,
					(LightSensorParameters) parameters);
		case CARBON_MONOXIDE:
			return new SensorCarbonMonoxide(platformId, type,
					(CarbonMonoxideParameters) parameters);
		case HUMIDITY: // fall
			return new SensorHumidity(platformId, type,
					(HumidityParameters) parameters);
		case LIGHT_SWITCH:
			return new SensorLightSwitch(platformId, type,
					(LightSwitchParameters) parameters, manager);
		case SERVO_WEBCAM:
			return new SensorWebcamServo(platformId, type,
					(WebcamParameters) parameters, manager);

			// Default action to take if the sensor is an unknown type
		default:
			assert (false);
			return new SensorNull();

		}
	}

}
