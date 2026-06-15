package michael.page.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "zona")
@NoArgsConstructor
@AllArgsConstructor
public class ZonaEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private String id;

  @Column(name = "soporte_refrigeracion", nullable = false)
  private boolean soporteRefrigeracion;

}
