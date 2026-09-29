package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import amigo.programador.hexagonal.domain.model.Client;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ClientResponse {
  private Long id;
  private String name;
  private Integer age;
  private String dni;

  public static Client toDomain(ClientResponse clientResponse) {
    return Client.builder()
      .id(clientResponse.getId())
      .name(clientResponse.getName())
      .age(clientResponse.getAge())
      .dni(clientResponse.getDni())
      .build();
  }

  public static ClientResponse fromDomain(Client client) {
    return ClientResponse.builder()
      .id(client.getId())
      .name(client.getName())
      .age(client.getAge())
      .dni(client.getDni())
      .build();
  }

}
