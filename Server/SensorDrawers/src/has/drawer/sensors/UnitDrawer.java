package has.drawer.sensors;

import has.definitions.CommonParams;
import has.definitions.FormValue;
import has.definitions.PageType;
import has.drawer.page.BasicElementDrawer;
import has.sensors.HasUnit;
import has.sensors.UnitCollection;
import has.sensors.core.TypeSensor;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class UnitDrawer
{

	/**
	 * Draws an icon for the given HasUnit
	 * 
	 * @param unit
	 *            the unit to draw the icon for
	 * @return an html string representing the unit icon
	 */
	public static String drawIcon(HasUnit unit, String encodedUrl)
	{

		SensorDataDrawer drawer = UnitDrawer.dataDrawerMap.get(unit
				.getTypeSensor());

		if (drawer == null)
			return "";

		return drawer.drawIcon(unit, encodedUrl);

	}

	/**
	 * Draws the generic details of a unit that are common to all units.
	 * 
	 * @param unit
	 *            the unit to draw the details for
	 * @param roomSet
	 *            a full set of rooms to be drawn
	 * @return html string representing the generic details
	 */
	public static String drawGenericDetails(HasUnit unit, Set<String> roomSet)
	{
		String details = "";

		String roomloc = unit.getRoomLocation();
		if (roomloc == null)
			roomloc = "None";

		details += "<div><label for='name'>Name:</label> <input type='text' name='"
				+ FormValue.unitDisplayName
				+ "' value='"
				+ unit.getDisplayName() + "' /></div>";

		details += "<div><label for='name'>Location:</label>"
				+ BasicElementDrawer.drawRoomSelect(unit, roomSet) + "</div>";

		return details;

	}

	/**
	 * Draws the expanded details of a unit if availble
	 * 
	 * @param unit
	 *            the unit to draw the details for
	 * @return html string representing the generic details
	 */
	public static String drawExpandedDetails(HasUnit unit)
	{

		SensorDataDrawer drawer = dataDrawerMap.get(unit.getTypeSensor());

		String details = drawer.drawExpandedDetails(unit);
		String htmlString = "";

		if (details != null)
			if (!details.equals(""))
			{

				htmlString += "<div class='expandedDetails'>";
				htmlString += details;
				htmlString += "</div>";

			}

		return htmlString;

	}

	/**
	 * Draws the representation of the given unit's shorthand abbreviated data
	 * 
	 * @param unit
	 *            the unit to represent
	 * @return the data this unit has, in abbreviated format
	 */
	public static String drawBasicData(HasUnit unit)
	{

		// lookup the proper sensor detail drawer and draw the basic details
		SensorDataDrawer dataDrawer = dataDrawerMap.get(unit.getTypeSensor());

		String dataString;
		if (dataDrawer != null)
			dataString = "<p id='" + unit.getUniqueId().toString() + "_value'>"
					+ dataDrawer.drawShortDetails(unit) + "</p>";
		else
			dataString = "";

		return dataString;
	}

	/**
	 * Draws all present controls, sorted into divs divided by room location.
	 * 
	 * @param units
	 *            the complete set of units to draw
	 * @param roomSet
	 *            the complete set of room locations
	 * @param encodedUrl
	 *            a base encoded URL to use for url rewriting in links and
	 *            forms.
	 * @return html string representing controls divided into rooms
	 */
	public static String drawControlsByRoom(UnitCollection units,
			Set<String> roomSet, String encodedUrl)
	{

		StringBuilder overview = new StringBuilder();

		// print the units in each room in their own divs
		for (String room : roomSet)
		{

			overview.append("<div class='room'>");
			overview.append("<div><h3>" + room + "</h3></div>");

			for (HasUnit unit : units.values())
			{

				if (unit.getRoomLocation() != null)
				{
					if (unit.getRoomLocation().equals(room))
					{

						overview.append("<div class='control'>");

						// get the associated data drawer
						SensorDataDrawer drawer = dataDrawerMap.get(unit
								.getTypeSensor());

						// draw the control form section for this control.
						String ctl = drawer.drawControlForm(unit, encodedUrl);

						if (ctl != null)
							overview.append(ctl);

						overview.append("</div>"); // close the control div
					}
				}

			}

			overview.append("</div>"); // close the room div

		}

		return overview.toString();
	}

	/**
	 * Draws all present controls that don't have an assigned room
	 * 
	 * @param units
	 *            the complete set of units to draw
	 * @param encodedUrl
	 *            a base encoded URL to use for url rewriting in links and
	 *            forms.
	 * @return html string representing controls that are unsorted
	 */
	public static String drawControlsUncategorized(UnitCollection units,
			String encodedUrl)
	{
		StringBuilder uncategorized = new StringBuilder();
		uncategorized
				.append("<div class='uncategorized'><h3>Uncategorized</h3>");

		for (HasUnit unit : units.values())
		{

			if (unit.getRoomLocation() == null)
			{

				uncategorized.append("<div class='control'>");

				// get the associated data drawer
				SensorDataDrawer drawer = dataDrawerMap.get(unit
						.getTypeSensor());

				// draw the control form section for this control.
				String ctl = drawer.drawControlForm(unit, encodedUrl);

				if (ctl != null)
					uncategorized.append(ctl);

				uncategorized.append("</div>"); // close the control div

			}

		}

		uncategorized.append("</div>"); // close the uncategorized div

		return uncategorized.toString();

	}

	/**
	 * Draws a label for the given unit that's a link with the parameters meant
	 * to take the user to the details page
	 * 
	 * @param unit
	 *            the unit to draw the label for
	 * @param encodedUrl
	 *            the base URL that can be appended to for parameters
	 * @param includeData
	 *            true displays the sensor's basic data along with the label.
	 * @return an html string representing the label + optional data
	 */
	public static String drawClickableLabel(HasUnit unit, String encodedUrl,
			boolean includeData)
	{

		String htmlString = "<div class='unitLabel'>";
		htmlString += "<a class='unitLabel' href='" + encodedUrl + "?";
		htmlString += FormValue.unitEditId + "=" + unit.getUniqueId() + "&";
		htmlString += CommonParams.page + "=" + PageType.UnitEditValidate;
		htmlString += "'>" + unit.getDisplayName() + "</a>";

		if (includeData)
		{

			String basicData = drawBasicData(unit);

			if (basicData != null)
				if (!basicData.equals(""))
				{
					htmlString += drawBasicData(unit);
				}

		}

		htmlString += "</div>";

		return htmlString;
	}

	/**
	 * Draws a complete listing of all units that aren't sorted into a
	 * particular room yet
	 * 
	 * @param units
	 *            all the units to be listed
	 * @param urlLink
	 *            the base URL to append parameters to
	 * @return the html string for all unsorted units.
	 */
	public static String drawUnitsUncategorized(UnitCollection units,
			String urlLink)
	{

		StringBuilder uncategorized = new StringBuilder();
		uncategorized
				.append("<div class='uncategorized'><h3>Uncategorized</h3>");
		for (HasUnit unit : units.values())
		{

			if (unit.getRoomLocation() == null)
			{

				uncategorized.append("<div class='sensor'>");

				uncategorized.append(UnitDrawer.drawIcon(unit, urlLink));

				// print this sensor name
				uncategorized.append(UnitDrawer.drawClickableLabel(unit,
						urlLink, true));

				uncategorized.append("</div>");
			}

		}

		return uncategorized.toString();

	}

	/**
	 * Draws a complete list of units, sorted into divs by room
	 * 
	 * @param units
	 *            complete list of units
	 * @param roomSet
	 *            complete set of rooms
	 * @param urlLink
	 *            the base URL to append parameters to
	 * @return an html string representing the units, sorted into divs by room
	 */
	public static String drawUnitsByRoom(UnitCollection units,
			Set<String> roomSet, String urlLink)
	{

		StringBuilder overview = new StringBuilder();

		// print the units in each room in their own divs
		for (String room : roomSet)
		{

			overview.append("<div class='room'>");
			overview.append("<div><h3>" + room + "</h3></div>");

			for (HasUnit unit : units.values())
			{

				if (unit.getRoomLocation() != null)
				{
					if (unit.getRoomLocation().equals(room))
					{

						overview.append("<div class='sensor'>");

						overview.append(UnitDrawer.drawIcon(unit, urlLink));

						// print this sensor name
						overview.append(UnitDrawer.drawClickableLabel(unit,
								urlLink, true));

						overview.append("</div>");
					}
				}

			}

			overview.append("</div>");

		}

		return overview.toString();
	}

	private final static Map<TypeSensor, SensorDataDrawer> dataDrawerMap;

	static
	{

		dataDrawerMap = new HashMap<TypeSensor, SensorDataDrawer>();

		dataDrawerMap.put(TypeSensor.TEMPERATURE, new TemperatureDataDrawer());
		dataDrawerMap.put(TypeSensor.SERVO_BLINDS, new BlindsDataDrawer());
		dataDrawerMap.put(TypeSensor.HUMIDITY, new HumidityDataDrawer());
		dataDrawerMap.put(TypeSensor.LIGHT, new BrightnessDataDrawer());
		dataDrawerMap.put(TypeSensor.TEMPERATURE, new TemperatureDataDrawer());
		dataDrawerMap.put(TypeSensor.CARBON_MONOXIDE,
				new CarbonMonoxideDataDrawer());
		dataDrawerMap.put(TypeSensor.LIGHT_SWITCH, new LightSwitchDataDrawer());
		dataDrawerMap.put(TypeSensor.SERVO_WEBCAM, new WebcamDataDrawer());
		dataDrawerMap.put(TypeSensor.THERMOSTAT, new ThermostatDataDrawer());

	}

}
