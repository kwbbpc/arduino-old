package has.parsing.units;

import has.sensors.core.TypeSensor;

import java.util.HashMap;
import java.util.Map;

public class UnitParserFactory
{

	private static final Map<TypeSensor, UnitParser> parserMap = new HashMap<TypeSensor, UnitParser>();

	static
	{
		parserMap.put(TypeSensor.SERVO_BLINDS, new BlindsParser());
		parserMap.put(TypeSensor.THERMOSTAT, new ThermostatParser());
		parserMap.put(TypeSensor.TEMPERATURE, new TemperatureParser());
		parserMap.put(TypeSensor.LIGHT, new LightSensorParser());
		parserMap.put(TypeSensor.LIGHT_SWITCH, new LightSwitchParser());
		parserMap.put(TypeSensor.SERVO_WEBCAM, new WebcamParser());
		parserMap.put(TypeSensor.CARBON_MONOXIDE, new CarbonMonoxideParser());
		parserMap.put(TypeSensor.HUMIDITY, new HumidityParser());
		parserMap.put(null, new NullParser());
		parserMap.put(TypeSensor.INVALID, new NullParser());

	}

	public static UnitParser getMessageParser(TypeSensor unitType)
	{
		return parserMap.get(unitType);
	}

}
