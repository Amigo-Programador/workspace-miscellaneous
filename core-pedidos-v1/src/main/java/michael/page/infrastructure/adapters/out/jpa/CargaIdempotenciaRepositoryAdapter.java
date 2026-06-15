package michael.page.infrastructure.adapters.out.jpa;

import michael.page.application.ports.out.CargaIdempotenciaRepositoryPort;
import michael.page.infrastructure.adapters.out.jpa.entity.CargaIdempotenciaEntity;
import michael.page.infrastructure.adapters.out.jpa.repository.CargaIdempotenciaJpaRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class CargaIdempotenciaRepositoryAdapter implements CargaIdempotenciaRepositoryPort {

  private final CargaIdempotenciaJpaRepository jpaRepository;

  public CargaIdempotenciaRepositoryAdapter(CargaIdempotenciaJpaRepository jpaRepository) {
    this.jpaRepository = jpaRepository;
  }

  @Override
  public boolean existFile(String idempotencyKey, String archivoHash) {
    return jpaRepository.existsByIdempotencyKeyAndArchivoHash(idempotencyKey, archivoHash);
  }

  @Override
  public void registerFile(String idempotencyKey, String archivoHash) {
    CargaIdempotenciaEntity entity = new CargaIdempotenciaEntity();
    entity.setId(UUID.randomUUID());
    entity.setIdempotencyKey(idempotencyKey);
    entity.setArchivoHash(archivoHash);
    entity.setCreatedAt(LocalDateTime.now());

    jpaRepository.save(entity);
  }
}
