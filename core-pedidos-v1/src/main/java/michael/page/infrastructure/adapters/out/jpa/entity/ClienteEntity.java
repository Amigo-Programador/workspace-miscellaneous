package michael.page.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
public class ClienteEntity {
  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "activo", nullable = false)
  private boolean activo;
}
