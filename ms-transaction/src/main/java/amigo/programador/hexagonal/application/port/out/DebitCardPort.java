package amigo.programador.hexagonal.application.port.out;

import amigo.programador.hexagonal.domain.model.DebitCard;
import reactor.core.publisher.Mono;

public interface DebitCardPort {

  public Mono<DebitCard> findByCardNumber(String cardNumber);

}
