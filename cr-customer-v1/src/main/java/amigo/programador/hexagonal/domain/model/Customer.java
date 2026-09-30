package amigo.programador.hexagonal.domain.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Customer {
  private String id;
  private String name;
  private String dni;
  private Long age;
  private LocalDate birthday;
}
