package michael.page.infrastructure.adapters.out.jpa.repository;

import michael.page.infrastructure.adapters.out.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, String> {
}
