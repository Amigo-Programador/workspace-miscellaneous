package amigo.programador.hexagonal.application.port.out;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepositoryPort {

  Flux<Transaction> findAll();

  Mono<Transaction> findByTransactionCode(String code);

  Mono<Transaction> save(Transaction transaction);

  Mono<Transaction> findById(Long id);

  Mono<Void> deleteById(Long id);

}