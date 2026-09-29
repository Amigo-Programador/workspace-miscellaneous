package amigo.programador.hexagonal.infrastructure.adapter.common;

import amigo.programador.hexagonal.domain.model.TransactionType;

public enum TransactionTypeInfrastructure {
  DEPOSIT, CASH_OUT, TRANSFER;

  public TransactionType toDomain() {
    return TransactionType.valueOf(this.name());
  }
}
