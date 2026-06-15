package michael.page.infrastructure.adapters.out.batch.writer;

import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import michael.page.infrastructure.adapters.out.jpa.entity.PedidoEntity;
import michael.page.infrastructure.adapters.out.jpa.mapper.PedidoEntityMapper;
import michael.page.infrastructure.adapters.out.jpa.repository.PedidoJpaRepository;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class PedidoItemWriter implements ItemWriter<Pedido> {

  private final PedidoJpaRepository pedidoJpaRepository;
  private final PedidoEntityMapper pedidoEntityMapper;

  public PedidoItemWriter(PedidoJpaRepository pedidoJpaRepository, PedidoEntityMapper pedidoEntityMapper) {
    this.pedidoJpaRepository = pedidoJpaRepository;
    this.pedidoEntityMapper = pedidoEntityMapper;
  }

  @Override
  public void write(Chunk<? extends Pedido> chunk) throws Exception {
    List<PedidoEntity> entities = chunk.getItems().stream()
      .map(pedidoEntityMapper::domainToEntity)
      .toList();

    pedidoJpaRepository.saveAll(entities);
  }
}
