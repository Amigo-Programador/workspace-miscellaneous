package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.application.port.out.RepositoryPort;
import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.infrastructure.adapter.out.dto.TransactionDocument;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class RepositoryAdapter implements RepositoryPort {

  private final MongoRepository mongoRepository;

  public RepositoryAdapter(MongoRepository mongoRepository) {
    this.mongoRepository = mongoRepository;
  }

  @Override
  public Flux<Transaction> findAll() {
    return mongoRepository.findAll()
      .map(tr -> TransactionDocument.toDomain(tr));
  }
}
