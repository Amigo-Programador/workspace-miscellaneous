package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import amigo.programador.hexagonal.domain.model.DebitCard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class DebitCardResponse {
  private Long id;
  private String cardNumber;
  private Double balance;
  private LocalDate expirationDate;
  private ClientResponse client;

  public static DebitCard toDomain(DebitCardResponse debitCardResponse) {
    return DebitCard.builder()
      .id(debitCardResponse.getId())
      .cardNumber(debitCardResponse.getCardNumber())
      .balance(debitCardResponse.getBalance())
      .expirationDate(debitCardResponse.getExpirationDate())
      .client(ClientResponse.toDomain(debitCardResponse.getClient()))
      .build();
  }

  public static DebitCardResponse fromDomain(DebitCard debitCard) {
    if (debitCard == null) return null;
    return DebitCardResponse.builder()
      .id(debitCard.getId())
      .cardNumber(debitCard.getCardNumber())
      .balance(debitCard.getBalance())
      .expirationDate(debitCard.getExpirationDate())
      .client(ClientResponse.fromDomain(debitCard.getClient()))
      .build();
  }
}
