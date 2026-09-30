package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.infrastructure.adapter.out.dto.TransactionDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MongoRepository extends ReactiveMongoRepository<TransactionDocument, Long> {
}
