package has.core.page;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NoActionPage implements Page
{

	public NoActionPage()
	{

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
	{

		// nothing to do on this page

		return;

	}

}
