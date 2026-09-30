package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@Builder
public class CustomerDocument {
  @Id
  private String id;

  @NotBlank
  private String name;

  @NotNull
  @Positive(message = "La edad debe ser mayor a 0")
  private Long age;

  @NotNull
  @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe contener exactamente 8 dígitos numéricos")
  @Indexed(unique = true)
  private String dni;

  @NotNull
  private LocalDate birthday;
}