package has.sensors.core;

/**
 * @author KBroadway The type of sensor enumeration
 * 
 */
public enum TypeSensor
{
	// DO NOT FORMAT!!!!
	//  @formatter:off

		TEMPERATURE(		"Temperature Sensor",			Interface.SENSOR,				0x00),
		THERMOSTAT(			"Thermostat",					Interface.CONTROL,				0x01),
		LIGHT(				"Light Sensor",					Interface.SENSOR,				0x10),
		LIGHT_SWITCH(		"Light Switch",					Interface.CONTROL,				0x20),
		SERVO_BLINDS(		"Blinds",						Interface.CONTROL,				0x30),
		SERVO_WEBCAM(		"Webcam",						Interface.VIDEO_AND_CONTROL,	0x31),
		CARBON_MONOXIDE(	"Carbon Monoxide Detector",		Interface.SENSOR,				0x40),
		HUMIDITY(			"Humidity Sensor",				Interface.SENSOR,				0x50),
		
		
		INVALID(			"Unknown sensor type",			Interface.SENSOR,				0xFF);
		//@formatter:on

	private String displayName;
	private int id;

	public enum Interface
	{
		SENSOR, CONTROL, VIDEO_AND_CONTROL,

		INVALID;

		@Override
		public String toString()
		{
			switch (this)
			{
			case SENSOR:
				return "Sensor";
			case CONTROL:
				return "Control";
			case VIDEO_AND_CONTROL:
				return "Webcam";
			default:
				return "Unit";
			}
		}
	}

	private Interface interfaceType;

	TypeSensor(String type, Interface interfaceType, int id)
	{
		this.displayName = type;
		this.id = id;
		this.interfaceType = interfaceType;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString()
	{
		return displayName;
	}

	/**
	 * @return gets the numeric id of a sensor
	 */
	public int toId()
	{
		return id;
	}

	public Interface getInterfaceType()
	{
		return interfaceType;
	}

	/**
	 * 
	 * Looks up a sensor id type and returns the enumeration for that sensor.
	 * 
	 * @param id
	 *            the numeric id of a sensor
	 * @return Type sensor type enum.
	 * 
	 */
	public static TypeSensor lookup(int id)
	{
		for (TypeSensor t : TypeSensor.values())
		{
			if (id == t.toId())
				return t;
		}

		return TypeSensor.INVALID;
	}

	/**
	 * Looks up a sensor display name and returns the enumeration for that
	 * sensor.
	 * 
	 * @param name
	 *            the display name of a sensor
	 * @return Type sensor type enum
	 */
	public static TypeSensor lookup(String name)
	{
		for (TypeSensor t : TypeSensor.values())
		{
			if (name.equalsIgnoreCase(t.toString()))
				return t;
		}

		return TypeSensor.INVALID;
	}
}
