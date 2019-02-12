package has.definitions;

public class PageFlow
{

	PageType onSuccess;

	public PageType getOnSuccess()
	{
		return onSuccess;
	}

	public PageType getOnFailure()
	{
		return onFailure;
	}

	PageType onFailure;

	public PageFlow(PageType success, PageType failure)
	{
		this.onSuccess = success;
		this.onFailure = failure;
	}

}
