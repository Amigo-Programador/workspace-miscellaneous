package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.domain.model.TransactionType;
import amigo.programador.hexagonal.infrastructure.adapter.common.TransactionTypeInfrastructure;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponse {
  private Long id;
  private String transactionCode;
  private Double transactionAmount;
  private DebitCardResponse originDebitCard;
  private DebitCardResponse destinationDebitCard;
  private LocalDateTime transactionDate;
  private TransactionTypeInfrastructure transactionType;

  public static Transaction toDomain(TransactionResponse transactionResponse) {
    return Transaction.builder()
      .id(transactionResponse.id)
      .transactionCode(transactionResponse.transactionCode)
      .transactionAmount(transactionResponse.transactionAmount)
      .origin(DebitCardResponse.toDomain(transactionResponse.originDebitCard))
      .destination(DebitCardResponse.toDomain(transactionResponse.destinationDebitCard))
      .transactionDate(transactionResponse.transactionDate)
      .transactionType(TransactionType.valueOf(transactionResponse.transactionType.name()))
      .build();
  }

  public static TransactionResponse fromDomain(Transaction transaction) {
    return TransactionResponse.builder()
      .id(transaction.getId())
      .transactionCode(transaction.getTransactionCode())
      .transactionAmount(transaction.getTransactionAmount())
      .originDebitCard(DebitCardResponse.fromDomain(transaction.getOrigin()))
      .destinationDebitCard(DebitCardResponse.fromDomain(transaction.getDestination()))
      .transactionDate(transaction.getTransactionDate())
      .transactionType(TransactionTypeInfrastructure.valueOf(transaction.getTransactionType().name()))
      .build();
  }

}
