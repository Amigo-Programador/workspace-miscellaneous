package michael.page.infrastructure.adapters.out.jpa;

import michael.page.application.ports.out.PersistirPedidosPort;
import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.jpa.entity.PedidoEntity;
import michael.page.infrastructure.adapters.out.jpa.mapper.PedidoEntityMapper;
import michael.page.infrastructure.adapters.out.jpa.repository.PedidoJpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PedidoJpaAdapter implements PersistirPedidosPort {

  private final PedidoJpaRepository pedidoJpaRepository;
  private final PedidoEntityMapper mapper;

  public PedidoJpaAdapter(PedidoJpaRepository pedidoJpaRepository, PedidoEntityMapper mapper) {
    this.pedidoJpaRepository = pedidoJpaRepository;
    this.mapper = mapper;
  }

  @Override
  public void guardarEnLote(List<Pedido> pedidos) {
    List<PedidoEntity> entities = pedidos.stream()
      .map(mapper::domainToEntity)
      .toList();

    pedidoJpaRepository.saveAll(entities);
  }
}
