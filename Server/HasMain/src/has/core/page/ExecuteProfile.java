package has.core.page;

import has.core.page.exceptions.PageProcessingException;
import has.core.page.exceptions.UnitNotFoundException;
import has.definitions.CommonParams;
import has.definitions.PageType;
import has.definitions.SessionAttributes;
import has.sensors.Control;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.user.HasProfile;
import has.user.User;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ExecuteProfile implements Page
{

	public ExecuteProfile()
	{

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnitNotFoundException, PageProcessingException
	{

		try
		{

			boolean success = true;

			// get the current user
			User user = (User) request.getSession().getAttribute(
					SessionAttributes.user);

			// get the sensor manager
			ISensorManager manager = (ISensorManager) request.getSession()
					.getAttribute(SessionAttributes.sensorManager);

			// pull out the requested profile
			UUID profileUUID = (UUID.fromString(request
					.getParameter(CommonParams.profileId)));

			HasProfile profile = user.getProfile(profileUUID);

			for (HasUnit unitProfile : profile.getControls())
			{

				try
				{

					// find the corresponding unit in the sensor manager
					Control control = (Control) manager.getUnitCollection()
							.get(unitProfile.getUniqueId().toString());

					// TODO: Fix profile execution by sending a saved parameter
					// set to a control
					/**
					 * // send the saved data control.sendData(((Control)
					 * unitProfile).getDataToSend());
					 **/
				}
				catch (Exception e)
				{
					success = false;
				}

			}

			if (!success)
				request.setAttribute(CommonParams.profileExecute,
						PageType.ProfileFailure);
			else
				request.setAttribute(CommonParams.profileExecute,
						PageType.ProfileSuccess);

		}
		catch (Exception e)
		{
			// catch the exception and wrap it in a HAS exception
			throw new PageProcessingException(e.getMessage());
		}
	}
}
