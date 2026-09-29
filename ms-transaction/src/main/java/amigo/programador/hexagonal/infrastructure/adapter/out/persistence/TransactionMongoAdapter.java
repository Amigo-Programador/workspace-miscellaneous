package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.application.port.out.TransactionRepositoryPort;
import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.infrastructure.adapter.out.dto.TransactionDocument;
import amigo.programador.hexagonal.infrastructure.adapter.out.mapper.TransactionDocumentMapper;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TransactionMongoAdapter implements TransactionRepositoryPort {

  private final TransactionMongoRepository mongoRepository;
  private final TransactionDocumentMapper transactionMapper;

  public TransactionMongoAdapter(TransactionMongoRepository mongoRepository,
                                 TransactionDocumentMapper transactionMapper) {
    this.mongoRepository = mongoRepository;
    this.transactionMapper = transactionMapper;
  }

  @Override
  public Flux<Transaction> findAll() {
    return mongoRepository.findAll()
      .map(transactionMapper::toDomain);
  }

  @Override
  public Mono<Transaction> findByTransactionCode(String code) {
    return mongoRepository.findByTransactionCode(code)
      .map(transactionMapper::toDomain);
  }

  @Override
  public Mono<Transaction> save(Transaction transaction) {
    TransactionDocument tDocument = transactionMapper.toDocument(transaction);
    return mongoRepository.save(tDocument)
      .map(transactionMapper::toDomain);
  }

  @Override
  public Mono<Transaction> findById(Long id) {
    return mongoRepository.findById(id)
      .map(transactionMapper::toDomain);
  }

  @Override
  public Mono<Void> deleteById(Long id) {
    return mongoRepository.deleteById(id);
  }
}
