package has.core.page;

import has.core.page.exceptions.PageProcessingException;
import has.core.page.exceptions.UnitNotFoundException;
import has.definitions.FormValue;
import has.definitions.SessionAttributes;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnitEditPageModify implements Page
{

	public UnitEditPageModify()
	{

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnitNotFoundException, PageProcessingException
	{

		// validate that the unit requested still exists in the map
		ISensorManager manager = (ISensorManager) request.getSession()
				.getAttribute(SessionAttributes.sensorManager);

		if (manager != null)
		{
			String id = request.getParameter(FormValue.unitEditId);
			if (!manager.getUnitCollection().containsKey(id))
			{
				throw new UnitNotFoundException(id);
			}
			else
			{

				// get the new name
				String displayNameNew = request
						.getParameter(FormValue.unitDisplayName);

				// get the room location
				String roomLocationNew = request
						.getParameter(FormValue.roomLocation);

				// if "New..." was selected to enter a new room name to the list
				if (roomLocationNew.equals(FormValue.roomLocationNewStr))
				{
					roomLocationNew = request
							.getParameter(FormValue.roomLocationExt);
				}

				HasUnit unit = manager.getUnitCollection().get(id);

				if (displayNameNew != null)
					if (!displayNameNew.equals(""))
						unit.setDisplayName(displayNameNew);

				if (roomLocationNew != null)
					if (!roomLocationNew.equals(""))
						if (!roomLocationNew.equalsIgnoreCase("None"))
							unit.setRoomLocation(roomLocationNew);

				return;
			}

		}
		else
			throw new PageProcessingException(
					"The unit manager couldn't be found... this means there's an issue with your http session.  Close the browser window and login again.");

	}
}
