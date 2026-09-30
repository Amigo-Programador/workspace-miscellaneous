package amigo.programador.hexagonal.application.port.out;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Flux;

public interface RepositoryPort {

  Flux<Transaction> findAll();

}
