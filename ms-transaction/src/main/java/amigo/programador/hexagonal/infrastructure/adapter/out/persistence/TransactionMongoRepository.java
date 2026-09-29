package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.infrastructure.adapter.out.dto.TransactionDocument;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionMongoRepository extends ReactiveMongoRepository<TransactionDocument, Long> {

  public Mono<TransactionDocument> findByTransactionCode(String transactionCode);

}