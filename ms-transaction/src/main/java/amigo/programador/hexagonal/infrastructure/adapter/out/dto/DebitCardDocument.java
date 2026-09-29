package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.DebitCard;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class DebitCardDocument {
  private Long id;
  private String cardNumber;
  private Double balance;
  @JsonFormat(pattern = "dd/MM/yyyy")
  private LocalDate expirationDate;
  private ClientDocument client;

}
