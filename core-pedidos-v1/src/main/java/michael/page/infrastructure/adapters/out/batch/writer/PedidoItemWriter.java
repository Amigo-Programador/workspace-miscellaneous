package michael.page.infrastructure.adapters.out.batch.writer;

import michael.page.application.ports.out.PersistirPedidosPort;
import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.jpa.entity.PedidoEntity;
import michael.page.infrastructure.adapters.out.jpa.mapper.PedidoEntityMapper;
import michael.page.infrastructure.adapters.out.jpa.repository.PedidoJpaRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoItemWriter implements ItemWriter<Pedido> {

  private final PersistirPedidosPort persistirPedidosPort;

  public PedidoItemWriter(PersistirPedidosPort persistirPedidosPort) {
    this.persistirPedidosPort = persistirPedidosPort;
  }

  @Override
  public void write(Chunk<? extends Pedido> chunk) throws Exception {
    List<Pedido> pedidosValidos = (List<Pedido>) chunk.getItems();

    persistirPedidosPort.guardarEnLote(pedidosValidos);
  }

}
