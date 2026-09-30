package amigo.programador.hexagonal.infrastructure.adapter.out.dto;

import amigo.programador.hexagonal.domain.model.Client;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Objects;

@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClientDocument {
  @Id
  @Field("_id")
  private Long id;
  private String name;
  private Integer age;
  private String dni;

  @PersistenceCreator
  public ClientDocument(Long id, String name, Integer age, String dni) {
    this.id = id;
    this.name = name;
    this.age = age;
    this.dni = dni;
  }

  public static Client toDomain(ClientDocument clientDocument) {
    if(Objects.isNull(clientDocument)) {
      return null;
    }

    return Client.builder()
      .id(clientDocument.id)
      .name(clientDocument.name)
      .age(clientDocument.age)
      .dni(clientDocument.dni)
      .build();
  }

  public static ClientDocument fromDomain(Client client) {
    if(Objects.isNull(client)) {
      return null;
    }

    return ClientDocument.builder()
      .id(client.getId())
      .name(client.getName())
      .age(client.getAge())
      .dni(client.getDni())
      .build();
  }
}