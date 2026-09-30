package amigo.programador.hexagonal.application.port.out;

import amigo.programador.hexagonal.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRepositoryPort {

  Flux<Customer> findAll();

  Mono<Customer> findById(String id);

  Mono<Customer> save(Customer customer);

  Mono<Boolean> validateDuplicateDni(String dni);
}
