package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.Client;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientDocument {
  private Long id;
  private String name;
  private Integer age;
  private String dni;

  public Client toDomain() {
    return Client.builder()
      .id(this.id)
      .name(this.name)
      .age(this.age)
      .dni(this.dni)
      .build();
  }
}
