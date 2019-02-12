package has.definitions;

public enum PageType
{

	//@formatter:off
	Login("/login.jsp"), 
	Logout("/login.jsp"),
	Thermostat("/thermostat.jsp"),
	Profile("/profile.jsp"), 
	CreateProfile("/createprofile.jsp"), 
	SaveProfile("/createprofile.jsp"),
	ExecuteProfile("/profile.jsp"),
	ProfileSuccess("/profile.jsp"),
	ProfileFailure("/profile.jsp"),
	DeleteProfile("/createprofile.jsp"), 
	System("/system.jsp"), 
	Room("/room.jsp"),
	UnitEditValidate("/unitedit.jsp"),
	UnitEditModify("/unitedit.jsp"), 
	Overview("/overview.jsp"), 
	Error("/error.jsp");
	//@formatter:on

	private String url;

	PageType(String url)
	{
		this.url = url;
	}

	public String getUrl()
	{
		return this.url;
	}

}
