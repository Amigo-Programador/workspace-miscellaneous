package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.infrastructure.adapter.common.TransactionTypeInfrastructure;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionDocument {

  @NotNull
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
  private TransactionTypeInfrastructure transactionTypeInfrastructure;


}