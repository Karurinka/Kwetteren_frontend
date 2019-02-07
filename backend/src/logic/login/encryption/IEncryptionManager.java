package logic.login.encryption;

public interface IEncryptionManager
{
  String encrypt(char[] password);
  boolean verify(char[] password, String hash);
}
