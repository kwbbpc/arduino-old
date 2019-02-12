package has.user;

import has.sensors.HasUnit;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class HasProfile
{

	// Profile Name
	String name;

	// Profile UUID
	UUID uuid;

	// List of controls and their settings
	List<HasUnit> controls;

	public HasProfile()
	{
		this.name = "New Profile";
		this.controls = new ArrayList<HasUnit>();
		this.uuid = UUID.randomUUID();
	}

	public List<HasUnit> getControls()
	{
		return controls;
	}

	public void setControls(List<HasUnit> profile)
	{

		if (profile == null)
			this.controls = new ArrayList<HasUnit>();
		else
			this.controls = profile;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public UUID getUuid()
	{
		return uuid;
	}

	public void setUuid(UUID uuid)
	{
		this.uuid = uuid;
	}
}
