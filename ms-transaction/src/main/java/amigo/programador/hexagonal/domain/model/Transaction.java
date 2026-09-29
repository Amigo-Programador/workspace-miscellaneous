package amigo.programador.hexagonal.domain.model;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Transaction {

  private Long id;
  private String transactionCode;
  private Double transactionAmount;
  private DebitCard origin;
  private DebitCard destination;
  private LocalDateTime transactionDate;
  private TransactionType transactionType;

  public Transaction processTransaction(DebitCard originCard, DebitCard destinationCard) {

    switch(transactionType) {
      case DEPOSIT:
        originCard.deposit(transactionAmount);
        this.origin = originCard;
        break;

      case CASH_OUT:
        originCard.withdraw(transactionAmount);
        this.origin = originCard;
        break;

      case TRANSFER:
        originCard.withdraw(transactionAmount);
        destinationCard.deposit(transactionAmount);
        this.origin = originCard;
        this.destination = destinationCard;
        break;

    }

    this.transactionDate = LocalDateTime.now();
    return this;
  }

}
