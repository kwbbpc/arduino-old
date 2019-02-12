package has.core.page;

import has.core.page.exceptions.InvalidLoginException;
import has.core.page.parameters.LoginParam;
import has.definitions.SessionAttributes;
import has.user.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginPage implements Page
{

	List<User> users;
	User activeUser;

	public LoginPage(List<User> registeredUsers)
	{
		this.users = registeredUsers;

	}

	private void login(String username, String password, HttpSession session)
			throws InvalidLoginException
	{

		User activeUser = null;

		// find the user in the list
		for (User user : users)
		{
			if (user.getUsername().equals(username))
			{
				activeUser = user;
				break;
			}
		}

		if (activeUser == null)
			throw new InvalidLoginException();

		if (activeUser.getPassword().equals(password))
		{
			// the user is logged in.
			session.setAttribute(SessionAttributes.user, activeUser);
			return;
		}
		else
		{
			// no one is logged in.
			session.setAttribute(SessionAttributes.user, null);
			throw new InvalidLoginException();
		}

	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws InvalidLoginException
	{

		String username = request.getParameter(LoginParam.username);
		String password = request.getParameter(LoginParam.password);
		HttpSession session = request.getSession();

		login(username, password, session);

		return;

	}

}
