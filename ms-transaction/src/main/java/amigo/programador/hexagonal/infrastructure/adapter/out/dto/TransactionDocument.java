package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.Objects;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "transaction")
public class TransactionDocument {
  @NotNull
  @Id
  @Field("_id")
  private Long id;
  @NotNull
  @Pattern(regexp = "^[0-9]{4}$", message = "Invalid transaction code, example: 4891")
  private String transactionCode;
  @NotNull
  private Double transactionAmount;
  @NotNull
  private DebitCardDocument origin;
  private DebitCardDocument destination;
  private LocalDateTime transactionDate;
  @NotNull
  @Field("transactionType")
  private TransactionType transactionType;

  @PersistenceCreator
  public TransactionDocument(Long id, String transactionCode, Double transactionAmount,
                             DebitCardDocument origin, DebitCardDocument destination,
                             LocalDateTime transactionDate, TransactionType transactionType) {
    this.id = id;
    this.transactionCode = transactionCode;
    this.transactionAmount = transactionAmount;
    this.origin = origin;
    this.destination = destination;
    this.transactionDate = transactionDate;
    this.transactionType = transactionType;
  }

  public static Transaction toDomain(TransactionDocument transactionDocument) {
    if(Objects.isNull(transactionDocument)) {
      return null;
    }

    return Transaction.builder()
      .id(transactionDocument.getId())
      .transactionAmount(transactionDocument.getTransactionAmount())
      .transactionCode(transactionDocument.getTransactionCode())
      .destination(DebitCardDocument.toDomain(transactionDocument.getDestination()))
      .origin(DebitCardDocument.toDomain(transactionDocument.getOrigin()))
      .transactionType(
        amigo.programador.hexagonal.domain.model.TransactionType.valueOf(transactionDocument.getTransactionType().name()))
      .transactionDate(transactionDocument.getTransactionDate())
      .build();
  }

  public static TransactionDocument fromDomain(Transaction transaction) {
    if(Objects.isNull(transaction)) {
      return null;
    }

    return TransactionDocument.builder()
      .id(transaction.getId())
      .transactionAmount(transaction.getTransactionAmount())
      .transactionCode(transaction.getTransactionCode())
      .destination(DebitCardDocument.fromDomain(transaction.getDestination()))
      .origin(DebitCardDocument.fromDomain(transaction.getOrigin()))
      .transactionType(
        TransactionType.valueOf(transaction.getTransactionType().name()))
      .transactionDate(transaction.getTransactionDate())
      .build();
  }

}
