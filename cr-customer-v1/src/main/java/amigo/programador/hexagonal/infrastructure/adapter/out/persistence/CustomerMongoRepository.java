package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface CustomerMongoRepository extends ReactiveMongoRepository<CustomerDocument, String> {

  Mono<Boolean> existsByDni(String dni);

}
