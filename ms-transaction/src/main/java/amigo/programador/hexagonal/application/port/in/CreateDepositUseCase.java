package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Transaction;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;

public interface CreateDepositUseCase {

  public Mono<Transaction> createDepositOrCashOut(Transaction transaction);

}
