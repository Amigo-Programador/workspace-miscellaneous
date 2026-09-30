package amigo.programador.hexagonal.application.service;

import amigo.programador.hexagonal.application.port.in.FindAllTransactionsUseCase;
import amigo.programador.hexagonal.application.port.out.RepositoryPort;
import amigo.programador.hexagonal.domain.model.Transaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TransactionService implements FindAllTransactionsUseCase {

  private final RepositoryPort repositoryPort;

  public TransactionService(RepositoryPort repositoryPort) {
    this.repositoryPort = repositoryPort;
  }

  public Flux<Transaction> findAll() {
    return repositoryPort.findAll();
  }


}
