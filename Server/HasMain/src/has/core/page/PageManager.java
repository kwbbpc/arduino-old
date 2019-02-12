package has.core.page;

import has.core.page.exceptions.PageErrorManager;
import has.core.page.exceptions.PageProcessingException;
import has.definitions.CommonParams;
import has.definitions.PageFlow;
import has.definitions.PageType;
import has.definitions.SessionAttributes;
import has.user.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PageManager
{

	private Map<PageType, Page> pageStrategy;
	private Map<PageType, PageFlow> pageFlow;

	private PageErrorManager errorManager;

	List<User> users;

	public PageManager()
	{

		this.users = new ArrayList<User>();
		this.pageStrategy = new HashMap<PageType, Page>();
		this.pageFlow = new HashMap<PageType, PageFlow>();
		this.errorManager = new PageErrorManager();

		initUsers();

		initPagemap();

	}

	/**
	 * Processes a request by pulling out a page parameter (if present) and
	 * executing the corresponding strategy for that page/action.
	 * 
	 * @param request
	 * @param response
	 * @return the next page enum, which contains a valid URL to send the client
	 *         to
	 */
	public PageType processRequest(HttpServletRequest request,
			HttpServletResponse response)
	{

		// push the page error manager into the session if it doesn't have one
		// yet
		if (request.getSession().getAttribute(SessionAttributes.errors) == null)
		{
			request.getSession().setAttribute(SessionAttributes.errors,
					errorManager);
		}

		PageType nextPage = PageType.Overview;

		// pull out the page request if there's a specific page parameter
		String pageStr = request.getParameter(CommonParams.page);

		PageType pageType = null;

		if (pageStr != null)
		{
			// get the page type of the page request from the parameter string
			pageType = PageType.valueOf(pageStr);
		}

		if (pageType == null)
			return nextPage;

		Page page = pageStrategy.get(pageType);

		if (page == null)
			return nextPage;

		try
		{
			page.execute(request, response);
			nextPage = pageFlow.get(pageType).getOnSuccess();
		}
		catch (PageProcessingException e)
		{
			nextPage = pageFlow.get(pageType).getOnFailure();
		}
		catch (NullPointerException e)
		{
			// the page doesn't exist
			nextPage = PageType.Error;
		}

		// make sure there's a user logged in for this session
		User user = (User) request.getSession().getAttribute(
				SessionAttributes.user);
		if (user == null)
			return PageType.Login;

		return nextPage;

	}

	private void initUsers()
	{
		User user = new User();
		try
		{
			user.setEmail("kybroadway@gmail.com");
			user.setName("kbroadway");
			user.setPassword("october2010");
			this.users.add(user);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void initPagemap()
	{
		pageStrategy.put(PageType.Login, new LoginPage(this.users));
		pageStrategy.put(PageType.Logout, new LogoutPage());
		pageStrategy.put(PageType.Overview, new NoActionPage());
		pageStrategy.put(PageType.UnitEditValidate, new UnitEditPageValidate());
		pageStrategy.put(PageType.UnitEditModify, new UnitEditPageModify());
		pageStrategy.put(PageType.Profile, new NoActionPage());
		pageStrategy.put(PageType.CreateProfile, new NoActionPage());
		pageStrategy.put(PageType.SaveProfile, new SaveProfile());
		pageStrategy.put(PageType.DeleteProfile, new DeleteProfile());
		pageStrategy.put(PageType.ExecuteProfile, new ExecuteProfile());
		pageStrategy.put(PageType.System, new NoActionPage());

		// @formatter:off
		
					 	// page type to execute/validate					next page on success, 		next page on fail
		pageFlow.put(	PageType.Login, 					new PageFlow(	PageType.Overview, 			PageType.Login));
		pageFlow.put(	PageType.Logout, 					new PageFlow(	PageType.Login, 			PageType.Login));
		pageFlow.put(	PageType.Overview, 					new PageFlow(	PageType.Overview, 			PageType.Error));
		pageFlow.put(	PageType.UnitEditValidate, 			new PageFlow(	PageType.UnitEditValidate, 	PageType.Error));
		pageFlow.put(	PageType.UnitEditModify, 			new PageFlow(	PageType.Overview, 			PageType.UnitEditModify));
		pageFlow.put(	PageType.Profile, 					new PageFlow(	PageType.Profile, 			PageType.Error));
		pageFlow.put(	PageType.CreateProfile, 			new PageFlow(	PageType.CreateProfile, 	PageType.Error)); 
		pageFlow.put(	PageType.SaveProfile, 				new PageFlow(	PageType.Profile, 			PageType.CreateProfile));
		pageFlow.put(	PageType.DeleteProfile, 			new PageFlow(	PageType.Profile, 			PageType.Error));
		pageFlow.put(	PageType.ExecuteProfile, 			new PageFlow(	PageType.ProfileSuccess, 	PageType.ProfileFailure));
		pageFlow.put(	PageType.System, 					new PageFlow(	PageType.System, 			PageType.Error));
		// @formatter:on

	}
}
