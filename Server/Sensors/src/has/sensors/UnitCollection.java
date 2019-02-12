package has.sensors;

import has.sensors.core.TypeSensor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author KBroadway
 * 
 *         Typedef of a sensor collection map. Provides a collection of sensors,
 *         mapped by the unique id.
 * 
 */
public class UnitCollection extends HashMap<String, HasUnit>
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Gets all sensors of a particular type
	 * 
	 * @param type
	 *            the sensor type to get
	 * @return the list of sensors
	 */
	public List<HasUnit> getByType(TypeSensor type)
	{
		List<HasUnit> byType = new ArrayList<HasUnit>();

		for (HasUnit unit : this.values())
		{
			if (unit.getTypeSensor() == type)
				byType.add(unit);
		}

		return byType;
	}

	/**
	 * Gets all the sensors that implement a particular interface
	 * 
	 * @param type
	 * @return
	 */
	public List<HasUnit> getByInterface(TypeSensor.Interface type)
	{
		List<HasUnit> byInterface = new ArrayList<HasUnit>();

		for (HasUnit unit : this.values())
		{
			if (unit.getTypeSensor().getInterfaceType() == type)
				byInterface.add(unit);
		}

		return byInterface;
	}

}
