package amigo.programador.hexagonal.infrastructure.adapter.in.web;

import amigo.programador.hexagonal.application.port.in.FindAllTransactionsUseCase;
import amigo.programador.hexagonal.domain.model.Transaction;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

  private final FindAllTransactionsUseCase findAllTransactionsUseCase;

  public TransactionController(FindAllTransactionsUseCase findAllTransactionsUseCase) {
    this.findAllTransactionsUseCase = findAllTransactionsUseCase;
  }

  @GetMapping("/findall")
  public Mono<ApiResponse<List<Transaction>>> findAll() {
    return findAllTransactionsUseCase.findAll()
      .collectList()
      .map(tr -> ApiResponse.success("All transactions", tr));
  }
}
