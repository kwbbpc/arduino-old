package has.core;

import has.core.page.PageManager;
import has.definitions.PageType;
import has.definitions.SessionAttributes;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ServeletCore
 */
@WebServlet("/has")
public class HasMain extends HttpServlet
{

	private static final long serialVersionUID = 1L;

	private PageManager pageManager;
	private HasSensors hasSensors;

	Date systemStartupTime;

	@Override
	public void init()
	{

		this.pageManager = new PageManager();
		this.hasSensors = new HasSensors();

		this.systemStartupTime = new Date();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{

		// update the session with the sensor data
		HttpSession session = request.getSession();

		if (session.getAttribute(SessionAttributes.sensorManager) == null)
			session.setAttribute(SessionAttributes.sensorManager,
					hasSensors.getSensorManager());
		if (session.getAttribute(SessionAttributes.serverStartTime) == null)
			session.setAttribute(SessionAttributes.serverStartTime,
					this.systemStartupTime.getTime());

		if (session.getAttribute(SessionAttributes.sessionTime) == null)
			session.setAttribute(SessionAttributes.sessionTime,
					new Date().getTime());

		// get the page parameter
		PageType nextPage = pageManager.processRequest(request, response);

		RequestDispatcher dispatcher = getServletContext()
				.getRequestDispatcher(response.encodeURL(nextPage.getUrl()));

		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
