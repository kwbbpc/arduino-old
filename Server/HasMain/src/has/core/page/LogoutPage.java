package has.core.page;

import has.core.page.exceptions.InvalidLoginException;
import has.user.User;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutPage implements Page
{

	List<User> users;
	User activeUser;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws InvalidLoginException
	{

		HttpSession session = request.getSession();

		session.invalidate();

		return;

	}
}
