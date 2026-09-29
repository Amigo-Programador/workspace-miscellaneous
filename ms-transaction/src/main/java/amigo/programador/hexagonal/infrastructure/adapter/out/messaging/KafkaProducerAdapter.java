package amigo.programador.hexagonal.infrastructure.adapter.out.messaging;

import amigo.programador.hexagonal.application.port.out.KafkaProducerPort;
import amigo.programador.hexagonal.domain.model.DebitCard;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerAdapter implements KafkaProducerPort {

  private static final String topic = "TRANSFERENCE";

  private final KafkaTemplate<String, Object> kafkaTemplate;

  private final ObjectMapper objectMapper;

  public KafkaProducerAdapter(KafkaTemplate<String, Object> kafkaTemplate, ObjectMapper objectMapper) {
    this.kafkaTemplate = kafkaTemplate;
    this.objectMapper = objectMapper;
  }

  public void updateDebitCardBalance(DebitCard debitCard) {

    try {
      System.out.println(">>> Sending DebitCard message");
      String debitCardString = objectMapper.writeValueAsString(debitCard);
      kafkaTemplate.send(topic, debitCardString);
      System.out.println(">>> Sent DebitCard message");
    } catch (JsonProcessingException ex) {
      throw new RuntimeException(ex);
    }

  }

}
