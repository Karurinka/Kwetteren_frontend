package logic.profile;

public interface IProfileManager
{
  boolean ChangeUsername(String oldUsername, String newUsername, char[] password);
  boolean ChangeBiography(String biography, char[] password);
}
