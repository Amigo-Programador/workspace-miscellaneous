package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import amigo.programador.hexagonal.domain.model.DebitCard;
import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.domain.model.TransactionType;
import amigo.programador.hexagonal.infrastructure.adapter.common.TransactionTypeInfrastructure;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequest {

  @NotNull(message = "El código de transacción es requerido")
  @Pattern(regexp = "^[0-9]{4}$", message = "Invalid transaction code, example: 4891")
  private String transactionCode;

  @NotNull(message = "El monto es requerido")
  @Positive(message = "El monto debe ser mayor a cero")
  private Double transactionAmount;

  @NotNull(message = "La tarjeta origen es requerida")
  private String originCardNumber;

  private String destinationCardNumber;

  @NotNull(message = "El tipo de transacción es requerido")
  private TransactionTypeInfrastructure transactionTypeInfrastructure;

  /* Tr
      TransactionRequest → Transaction (Domain)
   */
  public static Transaction toDomain(TransactionRequest transactionRequest) {
    return Transaction.builder()
      .transactionCode(transactionRequest.getTransactionCode())
      .transactionAmount(transactionRequest.getTransactionAmount())
      .origin(DebitCard.builder()
        .cardNumber(transactionRequest.getOriginCardNumber())
        .build())
      .destination(transactionRequest.getDestinationCardNumber() != null ?
        DebitCard.builder()
          .cardNumber(transactionRequest.getDestinationCardNumber())
          .build() : null)
      .transactionType(TransactionType.valueOf(transactionRequest.getTransactionTypeInfrastructure().name()))
      .build();
  }
}
