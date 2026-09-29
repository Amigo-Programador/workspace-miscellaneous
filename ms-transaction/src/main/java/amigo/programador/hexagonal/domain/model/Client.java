package amigo.programador.hexagonal.domain.model;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Client {

  private Long id;
  private String name;
  private Integer age;
  private String dni;

}