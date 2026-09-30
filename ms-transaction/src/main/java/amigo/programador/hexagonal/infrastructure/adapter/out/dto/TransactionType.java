package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

public enum TransactionType {
  DEPOSIT, CASH_OUT, TRANSFER;

  public amigo.programador.hexagonal.domain.model.TransactionType toDomain() {
    return amigo.programador.hexagonal.domain.model.TransactionType.valueOf(this.name());
  }
}
