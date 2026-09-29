package amigo.programador.hexagonal.infrastructure.config;

import amigo.programador.hexagonal.application.port.out.DebitCardPort;
import amigo.programador.hexagonal.application.port.out.KafkaProducerPort;
import amigo.programador.hexagonal.application.port.out.TransactionRepositoryPort;
import amigo.programador.hexagonal.application.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class BeanInitializerConfig {

  @Bean
  public TransactionService transactionService(TransactionRepositoryPort repositoryPort,
                                               DebitCardPort debitCardPort,
                                               KafkaProducerPort kafkaProducerPort) {
    return new TransactionService(repositoryPort, debitCardPort, kafkaProducerPort);
  }

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    return objectMapper;
  }


  @Bean
  public WebClient buildWebClient(@Value("${spring.application.consume.msdebitcard.url}") String url) {
    WebClient webMsClient = WebClient.create(url);
    return webMsClient;
  }

}
