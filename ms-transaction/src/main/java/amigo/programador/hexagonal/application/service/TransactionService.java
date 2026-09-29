package amigo.programador.hexagonal.application.service;


import amigo.programador.hexagonal.application.hexagonal.ValidateUtil;
import amigo.programador.hexagonal.application.port.in.CreateDepositUseCase;
import amigo.programador.hexagonal.application.port.in.CreateTransferenceUseCase;
import amigo.programador.hexagonal.application.port.in.FindAllTransactionsUseCase;
import amigo.programador.hexagonal.application.port.in.ProcessTransactionUseCase;
import amigo.programador.hexagonal.application.port.out.DebitCardPort;
import amigo.programador.hexagonal.application.port.out.KafkaProducerPort;
import amigo.programador.hexagonal.application.port.out.TransactionRepositoryPort;
import amigo.programador.hexagonal.domain.model.Transaction;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static amigo.programador.hexagonal.application.hexagonal.ValidateUtil.validateDuplicateLambda;

@Service
public class TransactionService implements ProcessTransactionUseCase, CreateTransferenceUseCase, CreateDepositUseCase, FindAllTransactionsUseCase {

  private final TransactionRepositoryPort transactionRepositoryPort;
  private final DebitCardPort debitCardPort;
  private final KafkaProducerPort kafkaProducerPort;


  public TransactionService(TransactionRepositoryPort transactionRepositoryPort,
                            DebitCardPort debitCardPort,
                            KafkaProducerPort kafkaProducerPort) {
    this.transactionRepositoryPort = transactionRepositoryPort;
    this.debitCardPort = debitCardPort;
    this.kafkaProducerPort = kafkaProducerPort;
  }

  public Flux<Transaction> findAll() {
    return transactionRepositoryPort.findAll();
  }

  @Override
  public Mono<Transaction> processTransaction(Transaction transaction) {
    switch (transaction.getTransactionType()) {
      case DEPOSIT:
      case CASH_OUT:
        return this.createDepositOrCashOut(transaction);
      case TRANSFER:
        return this.createTransference(transaction);
      default:
        return Mono.error(new IllegalArgumentException("Tipo de transacción no soportado"));
    }
  }

  public Mono<Transaction> createTransference(Transaction transaction) {

    return validateDuplicateLambda(transaction.getTransactionCode(),
                                  transactionRepositoryPort::findByTransactionCode)
      .then(debitCardPort.findByCardNumber(transaction.getOrigin().getCardNumber()))
      .flatMap(origin -> debitCardPort.findByCardNumber(transaction.getDestination().getCardNumber())
        .filter(destination -> origin.getBalance() > 0)
        .map(destination -> transaction.processTransaction(origin, destination))
        .flatMap(tr -> transactionRepositoryPort.save(tr)
          .doOnSuccess(trOk -> {
            kafkaProducerPort.updateDebitCardBalance(trOk.getOrigin());
            kafkaProducerPort.updateDebitCardBalance(trOk.getDestination());
          })));
  }

  public Mono<Transaction> createDepositOrCashOut(Transaction transaction) {

    return  validateDuplicateLambda(transaction.getTransactionCode(),  transactionRepositoryPort::findByTransactionCode)
      .then(debitCardPort.findByCardNumber(transaction.getOrigin().getCardNumber()))
      .map(origin -> transaction.processTransaction(origin, null))
      .filter(tr -> tr.getOrigin().getBalance() >= 0)
      .flatMap(tr -> transactionRepositoryPort.save(tr)
        .doOnSuccess(trCreated -> kafkaProducerPort.updateDebitCardBalance(trCreated.getOrigin()))
        .map(trCreated -> trCreated));
  }

}
