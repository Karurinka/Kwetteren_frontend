package logic.profile;

public class ProfileManager implements IProfileManager
{
  @Override
  public boolean ChangeUsername(String oldUsername, String newUsername, char[] password)
  {
    return false;
  }

  @Override
  public boolean ChangeBiography(String biography, char[] password)
  {
    return false;
  }
}
