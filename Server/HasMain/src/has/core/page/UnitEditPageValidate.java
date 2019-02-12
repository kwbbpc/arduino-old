package has.core.page;

import has.core.page.exceptions.PageProcessingException;
import has.core.page.exceptions.UnitNotFoundException;
import has.definitions.FormValue;
import has.definitions.SessionAttributes;
import has.sensors.ISensorManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UnitEditPageValidate implements Page
{

	public UnitEditPageValidate()
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
				// validation complete
				return;
			}

		}
		else
			throw new PageProcessingException(
					"The unit manager couldn't be found... this means there's an issue with your http session.  Close the browser window and login again.");

	}
}
