package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Mono;

public interface DeleteTransactionUseCase {

  public Mono<Transaction> deleteTransaction(Long id);

}
