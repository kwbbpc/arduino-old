package has.core.page;

import has.core.page.exceptions.PageProcessingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Page
{
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws PageProcessingException;
}
