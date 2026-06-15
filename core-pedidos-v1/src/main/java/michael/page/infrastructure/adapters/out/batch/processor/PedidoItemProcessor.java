package michael.page.infrastructure.adapters.out.batch.processor;

import michael.page.application.ports.in.CargarPedidosUseCase;
import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import michael.page.infrastructure.adapters.out.batch.mapper.PedidoBatchMapper;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

/*
 * Logica y procesamiento que el proceso batch debe seguir
 *
 */
@Component
public class PedidoItemProcessor implements ItemProcessor<PedidoCsv, Pedido> {

  private final CargarPedidosUseCase cargarPedidosUseCase;
  private final PedidoBatchMapper pedidoBatchMapper;

  public PedidoItemProcessor(CargarPedidosUseCase cargarPedidosUseCase,
                             PedidoBatchMapper pedidoBatchMapper) {
    this.cargarPedidosUseCase = cargarPedidosUseCase;
    this.pedidoBatchMapper = pedidoBatchMapper;
  }

  @Override
  public Pedido process(PedidoCsv pedidoCsv) throws Exception {
//    Pedido pedido = pedidoBatchMapper.toDomain
    return null;
  }

}
