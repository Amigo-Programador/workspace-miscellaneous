package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.DebitCard;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DebitCardDocument {
  @Id
  @Field("_id")
  private Long id;
  private String cardNumber;
  private Double balance;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate expirationDate;
  private ClientDocument client;

  @PersistenceCreator
  public DebitCardDocument(Long id, String cardNumber, Double balance, LocalDate expirationDate, ClientDocument client) {
    this.id = id;
    this.cardNumber = cardNumber;
    this.balance = balance;
    this.expirationDate = expirationDate;
    this.client = client;
  }

  public static DebitCard toDomain(DebitCardDocument debitCardDocument) {
    if(Objects.isNull(debitCardDocument)) {
      return null;
    }

    return DebitCard.builder()
      .id(debitCardDocument.getId())
      .cardNumber(debitCardDocument.getCardNumber())
      .balance(debitCardDocument.getBalance())
      .expirationDate(debitCardDocument.getExpirationDate())
      .client(ClientDocument.toDomain(debitCardDocument.getClient()))
      .build();
  }

  public static DebitCardDocument fromDomain(DebitCard debitCard) {
    if(Objects.isNull(debitCard)) {
      return null;
    }

    return DebitCardDocument.builder()
      .id(debitCard.getId())
      .cardNumber(debitCard.getCardNumber())
      .balance(debitCard.getBalance())
      .expirationDate(debitCard.getExpirationDate())
      .client(ClientDocument.fromDomain(debitCard.getClient()))
      .build();
  }
}
