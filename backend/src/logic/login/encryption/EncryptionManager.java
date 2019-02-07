package logic.login.encryption;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.stereotype.Component;

@Component
public final class EncryptionManager implements IEncryptionManager
{
  private final Argon2 argon2;

  public EncryptionManager() {
    argon2 = Argon2Factory.create();
  }

  @Override
  public String encrypt(char[] password)
  {
    try {
      String hash = argon2.hash(2, 65536, 1, password);

      if (argon2.verify(hash, password))
      {
        return hash;
      }
      else
      {
        return null;
      }
    }
    finally
    {
      argon2.wipeArray(password);
    }
  }

  @Override
  public boolean verify(char[] password, String hash)
  {
    if (argon2.verify(hash, password))
    {
      argon2.wipeArray(password);
      return true;
    }
    else
    {
      argon2.wipeArray(password);
      return false;
    }
  }
}

