package has.drawer.sensors;

import has.definitions.CommonParams;
import has.definitions.FormValue;
import has.definitions.PageType;
import has.drawer.page.BasicElementDrawer;
import has.sensors.HasUnit;
import has.sensors.impl.SensorThermostat;
import has.sensors.state.SensorState;

import java.util.HashMap;
import java.util.Map;

public class ThermostatDataDrawer implements SensorDataDrawer
{

	private static final Map<SensorState, String> imgMap;

	static
	{
		imgMap = new HashMap<SensorState, String>() {
			private static final long serialVersionUID = 1L;

			{
				put(null, "img/thermostat.png");
			}
		};
	}

	@Override
	public String drawShortDetails(HasUnit unit)
	{

		return "";

	}

	@Override
	public String drawExpandedDetails(HasUnit unit)
	{

		SensorThermostat thermostat = (SensorThermostat) unit;

		String htmlString = "<div class='thermostatContainer'>";

		htmlString += "<div class='thermostat'><p>Average: "
				+ thermostat.getAverageTemperature() + "&degF</p>";

		htmlString += BasicElementDrawer.drawHasCheckbox("average");
		htmlString += "Keep average at:<input type='text' name='average' />";

		htmlString += "</div>";

		if (!thermostat.getManagedRooms().isEmpty())
		{
			htmlString += "<div class='expandedThermostat'>";

			for (String room : thermostat.getManagedRooms())
			{
				htmlString += "<div class='thermostat'><p>";
				htmlString += room + ": " + thermostat.getRoomTemperature(room)
						+ "&degF</p>"
						+ BasicElementDrawer.drawHasCheckbox(room);

				htmlString += "Keep " + room + " at:<input type='text' name='"
						+ room + "' />";

				htmlString += "</div>";

			}

			htmlString += "</div>";
		}
		htmlString += "</div>";

		return htmlString;

	}

	@Override
	public String drawIcon(HasUnit unit, String encodedUrl)
	{
		String htmlString = "<img class='clickableImg' src='"
				+ imgMap.get(null) + "' onclick=\"location.href='" + encodedUrl
				+ "?";
		htmlString += FormValue.unitEditId + "=" + unit.getUniqueId() + "&";
		htmlString += CommonParams.page + "=" + PageType.UnitEditValidate;
		htmlString += "'\"/>";

		return htmlString;

	}

	@Override
	public String drawControlForm(HasUnit unit, String encodedUrl)
	{
		// thermostat controls are drawn separately.
		return null;
	}
}
