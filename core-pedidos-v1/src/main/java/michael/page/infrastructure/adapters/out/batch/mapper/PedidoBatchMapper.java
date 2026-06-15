package michael.page.infrastructure.adapters.out.batch.mapper;

import michael.page.domain.model.EstadoPedido;
import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Component
public class PedidoBatchMapper {

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  public Pedido csvToDomain(PedidoCsv pedidoCsv) {
    return new Pedido(
      UUID.randomUUID(),
      pedidoCsv.getNumeroPedido(),
      pedidoCsv.getClienteId(),
      pedidoCsv.getZonaEntrega(),
      LocalDate.parse(pedidoCsv.getFechaEntrega(), DATE_FORMATTER),
      EstadoPedido.valueOf(pedidoCsv.getEstado().toUpperCase()),
      Boolean.valueOf(pedidoCsv.getRequiereRefrigeracion()));
  }
}
