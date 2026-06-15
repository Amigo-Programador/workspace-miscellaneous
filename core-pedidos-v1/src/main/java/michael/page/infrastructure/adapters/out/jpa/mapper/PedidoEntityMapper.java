package michael.page.infrastructure.adapters.out.jpa.mapper;

import michael.page.domain.model.EstadoPedido;
import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.jpa.entity.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PedidoEntityMapper {

  // Pedido domain → Entity DB (para guardar en PostgreSQL)
  public PedidoEntity domainToEntity(Pedido domain) {
    if(Objects.isNull(domain)) {
      return null;
    }

    return new PedidoEntity(
      domain.getId(),
      domain.getNumeroPedido(),
      domain.getClienteId(),
      domain.getZonaId(),
      domain.getFechaEntrega(),
      domain.getEstado().name(),
      domain.isRequiereRefrigeracion());

  }

  // Entity DB → Pedido domain (para transferirlo al Port → Adapter)
  public Pedido entityToDomain(PedidoEntity entity) {
    if(Objects.isNull(entity)) {
      return null;
    }

    return new Pedido(
      entity.getId(),
      entity.getNumeroPedido(),
      entity.getClienteId(),
      entity.getZonaId(),
      entity.getFechaEntrega(),
      EstadoPedido.valueOf(entity.getEstado()),
      entity.isRequiereRefrigeracion());

  }
}
