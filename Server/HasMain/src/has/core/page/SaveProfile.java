package has.core.page;

import has.core.page.exceptions.PageProcessingException;
import has.core.page.exceptions.UnitNotFoundException;
import has.definitions.FormValue;
import has.definitions.SessionAttributes;
import has.sensors.HasUnit;
import has.sensors.ISensorManager;
import has.user.HasProfile;
import has.user.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SaveProfile implements Page
{

	public SaveProfile()
	{

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnitNotFoundException, PageProcessingException
	{

		try
		{

			ISensorManager sensorManager = (ISensorManager) request
					.getSession().getAttribute(SessionAttributes.sensorManager);

			// get the current user
			User user = (User) request.getSession().getAttribute(
					SessionAttributes.user);

			// get the list of profiles
			List<HasProfile> profileList = user.getSavedProfiles();

			// get the controls that were specified in this profile
			ArrayList<String> controlIdList = new ArrayList<String>(
					Arrays.asList(request
							.getParameterValues(FormValue.checkboxes)));

			// create a new profile
			HasProfile profile = new HasProfile();
			String profileName = request.getParameter(FormValue.profileName);

			if (profileName == null || profileName.equals(""))
				throw new PageProcessingException(
						"There wasn't a profile name specified.");

			profile.setName(profileName);

			for (String controlId : controlIdList)
			{
				try
				{
					// get the unit from the sensor manager
					HasUnit unit = sensorManager.getUnitCollection().get(
							controlId);

					String[] values = request.getParameterValues(controlId);

					// get the values to save from the request
					List<String> data = new ArrayList<String>(
							Arrays.asList(values));

					// TODO: Fix controls by saving a list of control parameters
					// to a profile UUID.
					/**
					 * HasUnit profileControl = SensorFactory.createSensor(
					 * sensorManager, unit.getTypeSensor(),
					 * unit.getPlatformId(), new ArrayList<String>());
					 * 
					 * Control control = (Control) profileControl;
					 * control.saveDataToSend(data);
					 * 
					 * profile.getControls().add(profileControl);
					 **/
				}
				catch (Exception e)
				{
					// invalid unit to save to a profile, or no data to save.
					continue;
				}

			}

			// add the profile to the user's profiles
			user.getSavedProfiles().add(profile);

		}
		catch (Exception e)
		{
			// catch the exception and wrap it in a HAS exception
			throw new PageProcessingException(e.getMessage());
		}
	}
}
