package michael.page.infrastructure.adapters.out.jpa.repository;

import michael.page.infrastructure.adapters.out.jpa.entity.CargaIdempotenciaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CargaIdempotenciaJpaRepository extends JpaRepository<CargaIdempotenciaEntity, String> {

  boolean existsByIdempotencyKeyAndArchivoHash(String idempotencyKey, String archivoHash);

}
