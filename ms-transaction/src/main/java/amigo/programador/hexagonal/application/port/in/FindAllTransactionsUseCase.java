package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Flux;

public interface FindAllTransactionsUseCase {

  public Flux<Transaction> findAll();

}
