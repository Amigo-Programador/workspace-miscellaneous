package amigo.programador.hexagonal.domain.model;

import amigo.programador.hexagonal.application.hexagonal.BusinessRuleException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class DebitCard {

  private Long id;
  private String cardNumber;
  private Double balance;
  private LocalDate expirationDate;
  private Client client;

  public void deposit(Double amount) {
    if (amount <= 0) {
      throw new BusinessRuleException("El monto a depositar debe ser mayor a cero");
    }
    this.balance += amount;
  }

  public void withdraw(Double amount) {
    if (amount <= 0) {
      throw new BusinessRuleException("El monto a retirar debe ser mayor a cero");
    }
    if (this.balance < amount) {
      throw new BusinessRuleException("Saldo insuficiente");
    }
    this.balance -= amount;
  }

}
