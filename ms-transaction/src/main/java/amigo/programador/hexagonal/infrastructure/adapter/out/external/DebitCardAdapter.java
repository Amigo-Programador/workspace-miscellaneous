package amigo.programador.hexagonal.infrastructure.adapter.out.external;

import amigo.programador.hexagonal.application.hexagonal.ApiResponse;
import amigo.programador.hexagonal.application.port.out.DebitCardPort;
import amigo.programador.hexagonal.domain.model.DebitCard;
import amigo.programador.hexagonal.infrastructure.adapter.out.mapper.TransactionDocumentMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class DebitCardAdapter implements DebitCardPort {

  private ObjectMapper objectMapper;
  private WebClient webClient;
  private TransactionDocumentMapper transactionDocumentMapper;

  public DebitCardAdapter(ObjectMapper objectMapper, WebClient webClient, TransactionDocumentMapper transactionDocumentMapper) {
    this.objectMapper = objectMapper;
    this.webClient = webClient;
    this.transactionDocumentMapper = transactionDocumentMapper;
  }

  public Mono<DebitCard> findByCardNumber(String cardNumber) {
    return webClient.get().uri("/findbycardnumber/{cardNumber}", cardNumber)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(ApiResponse.class)
      .filter(apiResponse -> Objects.nonNull(apiResponse.getResponse()))
      .map(apiResponse -> objectMapper.convertValue(
                                      apiResponse.getResponse(),
                                      DebitCard.class))
//      .map(transactionDocumentMapper::toDomain)
      .onErrorResume(HttpClientErrorException.NotFound.class, ex -> Mono.empty());
  }

}
