package has.core.page;

import has.core.page.exceptions.PageProcessingException;
import has.core.page.exceptions.UnitNotFoundException;
import has.definitions.CommonParams;
import has.definitions.SessionAttributes;
import has.user.User;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProfile implements Page
{

	public DeleteProfile()
	{

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws UnitNotFoundException, PageProcessingException
	{

		try
		{

			// get the current user
			User user = (User) request.getSession().getAttribute(
					SessionAttributes.user);

			// delete the profile
			user.removeProfile(UUID.fromString(request
					.getParameter(CommonParams.profileId)));

		}
		catch (Exception e)
		{
			// catch the exception and wrap it in a HAS exception
			throw new PageProcessingException(e.getMessage());
		}
	}
}
