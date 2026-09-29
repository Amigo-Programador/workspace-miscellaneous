package amigo.programador.hexagonal.application.port.out;

import amigo.programador.hexagonal.domain.model.DebitCard;

public interface KafkaProducerPort {

  public void updateDebitCardBalance(DebitCard debitCard);

}
