package has.user;

import has.user.exceptions.InvalidUserSettingException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 443485751219096424L;

	private String name;
	private String password;
	private String email;

	private List<HasProfile> savedProfiles;

	public User()
	{
		savedProfiles = new ArrayList<HasProfile>();
	}

	public String getUsername()
	{
		return name;
	}

	/**
	 * Sets the user's name
	 * 
	 * @param name
	 * @throws InvalidUserSettingException
	 *             thrown if the name isn't valid
	 */
	public void setName(String name) throws InvalidUserSettingException
	{

		if (name == null)
			throw new InvalidUserSettingException(
					"You must specify a username.");

		if (name.equals(""))
			throw new InvalidUserSettingException(
					"You must specify a username.");

		this.name = name;
	}

	public String getPassword()
	{
		return password;
	}

	/**
	 * Sets the user's password
	 * 
	 * @param password
	 * @throws InvalidUserSettingException
	 *             thrown if the password isn't valid
	 */
	public void setPassword(String password) throws InvalidUserSettingException
	{

		if (password == null)
			throw new InvalidUserSettingException(
					"You must specify a password.");

		if (password.equals(""))
			throw new InvalidUserSettingException(
					"Your password can't be blank.");

		this.password = password;
	}

	public String getEmail()
	{
		return email;
	}

	/**
	 * Sets the user's email
	 * 
	 * @param email
	 * @throws InvalidUserSettingException
	 *             thrown if the email isn't valid
	 */
	public void setEmail(String email) throws InvalidUserSettingException
	{

		if (email == null)
			throw new InvalidUserSettingException(
					"You must specify an email address.");

		if (email.equals(""))
			throw new InvalidUserSettingException(
					"You must specify an email address.");

		this.email = email;
	}

	public List<HasProfile> getSavedProfiles()
	{
		return savedProfiles;
	}

	/**
	 * Removes a saved profile by UUID
	 * 
	 * @param profileUUID
	 *            the UUID of the profile to remove
	 * @return true if a profile was removed
	 */
	public boolean removeProfile(UUID profileUUID)
	{
		for (HasProfile profile : savedProfiles)
		{
			if (profile.getUuid().equals(profileUUID))
			{
				savedProfiles.remove(profile);
				return true;
			}
		}
		return false;
	}

	/**
	 * Gets a saved profile by UUID
	 * 
	 * @param profileUUID
	 *            the UUID of the profile to get
	 * @return HasProfile
	 */
	public HasProfile getProfile(UUID profileUUID)
	{
		for (HasProfile profile : savedProfiles)
		{
			if (profile.getUuid().equals(profileUUID))
			{
				return profile;
			}
		}

		// not found, return an empty profile
		return new HasProfile();
	}

	public void setSavedProfiles(List<HasProfile> savedProfiles)
	{

		if (savedProfiles == null)
			this.savedProfiles = new ArrayList<HasProfile>();
		else
			this.savedProfiles = savedProfiles;
	}

	/**
	 * TODO: write the user's profile out to disk for saves between server
	 * shutdown/startup
	 * 
	 */
	public void saveUser()
	{
		// FileOutputStream fout;
		// try
		// {
		// fout = new FileOutputStream("userInfo.has");
		// ObjectOutputStream oos = new ObjectOutputStream(fout);
		// oos.writeObject(this);
		// oos.close();
		// } catch (Exception e)
		// {
		// e.printStackTrace();
		// }
	}

}
