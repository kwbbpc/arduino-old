package has.drawer.page;

import has.definitions.FormValue;
import has.sensors.HasUnit;
import has.sensors.SensorUniqueId;

import java.util.Set;

public class BasicElementDrawer
{

	/**
	 * Draws an image checkbox, controlled by CSS class hasChkBox
	 * 
	 * @param uniqueId
	 *            the unique unit Id to be used for the checkbox's
	 *            value/for:label values
	 * @return the html checkbox
	 */
	public static String drawHasCheckbox(SensorUniqueId uniqueId)
	{

		return "<input class='hasChkBx' type='checkbox' name='"
				+ FormValue.checkboxes + "' value='" + uniqueId.toString()
				+ "' id='" + uniqueId.toString() + "'/><label for='"
				+ uniqueId.toString() + "'></label>";

	}

	/**
	 * Draws an image checkbox, controlled by CSS class hasChkBox
	 * 
	 * @param value
	 *            the string to be used for the checkbox's value/for:label
	 *            values
	 * @return the html checkbox
	 */
	public static String drawHasCheckbox(String value)
	{

		return "<input class='hasChkBx' type='checkbox' name='"
				+ FormValue.checkboxes + "' value='" + value + "' id='"
				+ value + "'/><label for='" + value + "'></label>";

	}

	/**
	 * Draws a dropdown room selection box
	 * 
	 * @param unit
	 *            the HasUnit to use as the initial selection
	 * @param roomSet
	 *            the full set of rooms avaialable
	 * @return an html string for the dropdown box with an initial room
	 *         selection.
	 */
	public static String drawRoomSelect(HasUnit unit, Set<String> roomSet)
	{
		String select = "<select id='roomSelect' name='"
				+ FormValue.roomLocation
				+ "' onchange='onRoomSelectChange(this.form.roomSelect)'>";

		select += "<option value='none' ";

		if (unit.getRoomLocation() != null)
			if (unit.getRoomLocation().equalsIgnoreCase("none"))
				select += "select='selected'";

		select += ">None</option>";

		for (String room : roomSet)
		{

			// skip over the new string if it's present to add it last
			if (room.equals(FormValue.roomLocationNewStr))
				continue;

			select += "<option value='" + room + "' ";

			if (unit.getRoomLocation() != null)
				if (unit.getRoomLocation().equalsIgnoreCase(room))
					select += "selected";

			select += ">" + room + "</option>";
		}

		// Add the "New..." room last always
		if (roomSet.contains(FormValue.roomLocationNewStr))
		{
			select += "<option value='" + FormValue.roomLocationNewStr + "' >"
					+ FormValue.roomLocationNewStr + "</option>";
		}

		select += "</select>";

		return select;

	}

}
