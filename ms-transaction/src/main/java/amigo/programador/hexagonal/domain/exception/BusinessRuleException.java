package amigo.programador.hexagonal.domain.exception;

public class BusinessRuleException extends RuntimeException {

  private final String errorCode;

  public BusinessRuleException(String message) {
    super(message);
    this.errorCode = "BUSINESS_RULE_VIOLATION";
  }

  public String getErrorCode() {
    return errorCode;
  }
}
