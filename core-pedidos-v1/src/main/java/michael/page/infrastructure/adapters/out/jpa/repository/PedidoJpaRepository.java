package michael.page.infrastructure.adapters.out.jpa.repository;

import michael.page.infrastructure.adapters.out.jpa.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, UUID> {

  public boolean existsByNumeroPedido(String numeroPedido);
}
