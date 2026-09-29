package amigo.programador.hexagonal.infrastructure.adapter.in.web;

import amigo.programador.hexagonal.application.hexagonal.ApiResponse;
import amigo.programador.hexagonal.application.port.in.FindAllTransactionsUseCase;
import amigo.programador.hexagonal.application.port.in.ProcessTransactionUseCase;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.TransactionRequest;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.TransactionResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

import static amigo.programador.hexagonal.application.hexagonal.ApiResponse.success;


@RestController
@RequestMapping("/transaction")
public class TransactionController {
  private final ProcessTransactionUseCase processTransactionUseCase;
  private final FindAllTransactionsUseCase findAllTransactionsUseCase;

  public TransactionController(ProcessTransactionUseCase processTransactionUseCase,
                               FindAllTransactionsUseCase findAllTransactionsUseCase) {

    this.processTransactionUseCase = processTransactionUseCase;
    this.findAllTransactionsUseCase = findAllTransactionsUseCase;
  }

  @GetMapping("/findall")
  public Mono<ApiResponse> findAll() {
    return findAllTransactionsUseCase.findAll()
      .collectList()
      .map(transactions -> success("All transactions", transactions));
  }

  @PostMapping("/create")
  public Mono<ApiResponse<TransactionResponse>> create(@Valid @RequestBody TransactionRequest transactionRequest) {
    return processTransactionUseCase.processTransaction(TransactionRequest.toDomain(transactionRequest))
      .map(t -> TransactionResponse.fromDomain(t))
      .map(t -> success("Transacción procesada correctamente", t))
      .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND, "DebitCard origin not found")));
  }

}
