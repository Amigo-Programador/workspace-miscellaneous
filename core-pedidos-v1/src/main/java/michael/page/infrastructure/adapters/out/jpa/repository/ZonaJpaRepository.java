package michael.page.infrastructure.adapters.out.jpa.repository;

import michael.page.infrastructure.adapters.out.jpa.entity.ZonaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ZonaJpaRepository extends JpaRepository<ZonaEntity, String> {

}
