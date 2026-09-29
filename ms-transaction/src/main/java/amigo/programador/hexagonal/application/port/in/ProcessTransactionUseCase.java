package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface ProcessTransactionUseCase {

  Mono<Transaction> processTransaction(Transaction transaction);

}
