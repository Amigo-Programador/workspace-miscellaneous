package michael.page.infrastructure.adapters.out.jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(
  name = "pedidos",
  uniqueConstraints = {
    @UniqueConstraint(name = "UK_pedido_numero", columnNames = "numero_pedido")
  },
  indexes = {
    @Index(name = "IDX_pedido_estado_fecha", columnList = "estado, fecha_entrega")
  }
)
public class PedidoEntity {

  @Id
  @Column(name = "id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "numero_pedido", nullable = false, length = 20)
  private String numeroPedido;

  @Column(name = "cliente_id", nullable = false, length = 50)
  private String clienteId;

  @Column(name = "zona_id", nullable = false, length = 50)
  private String zonaId;

  @Column(name = "fecha_entrega", nullable = false)
  private LocalDate fechaEntrega;

  @Column(name = "estado", nullable = false, length = 20)
  private String estado;

  @Column(name = "requiere_refrigeracion", nullable = false)
  private boolean requiereRefrigeracion;

  @Column(name = "created_at", nullable = false, updatable = false)
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  @PrePersist
  protected void onCreate() {
    this.createdAt = LocalDateTime.now();
  }

  @PreUpdate
  protected void onUpdate() {
    this.updatedAt = LocalDateTime.now();
  }

  public PedidoEntity() {
  }

  public PedidoEntity(UUID id, String numeroPedido, String clienteId, String zonaId,
                      LocalDate fechaEntrega, String estado, boolean requiereRefrigeracion) {
    this.id = id;
    this.numeroPedido = numeroPedido;
    this.clienteId = clienteId;
    this.zonaId = zonaId;
    this.fechaEntrega = fechaEntrega;
    this.estado = estado;
    this.requiereRefrigeracion = requiereRefrigeracion;
  }
}
