package has.core.page.exceptions;

import java.util.ArrayList;
import java.util.List;

public class PageErrorManager
{

	private List<String> errorList;

	public PageErrorManager()
	{
		this.errorList = new ArrayList<String>();
	}

	public boolean containsErrors()
	{
		return !this.errorList.isEmpty();
	}

	public void clearErrors()
	{
		this.errorList.clear();
	}

	public List<String> getErrorList()
	{
		return errorList;
	}

	public void setErrorList(List<String> errorList)
	{
		this.errorList = errorList;
	}

	public void addError(String error)
	{
		this.errorList.add(error);
	}

}
