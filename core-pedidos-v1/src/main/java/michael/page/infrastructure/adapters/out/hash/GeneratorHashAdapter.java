package michael.page.infrastructure.adapters.out.hash;

import michael.page.application.ports.out.GeneradorHashPort;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.MessageDigest;

@Component
public class GeneratorHashAdapter implements GeneradorHashPort {

  @Override
  public String generarHash(byte[] data) {
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(data);
      return String.format("%064x", new BigInteger(1, hash));
    } catch (Exception ex) {
      throw new RuntimeException("Error calculando Hash SHA-256", ex);
    }
  }

}
