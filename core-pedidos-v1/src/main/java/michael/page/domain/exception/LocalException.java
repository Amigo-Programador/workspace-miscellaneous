package michael.page.domain.exception;

public class LocalException extends RuntimeException {
  private final String tipoError;

  public LocalException(String tipoError, String message) {
    super(message);
    this.tipoError = tipoError;
  }

  public String getTipoError() {
    return tipoError;
  }

}
