package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerUseCase {

  Mono<Customer> createCustomer(Customer customer);

  Mono<Customer> findCustomerById(String id);

  Flux<Customer> getAllCustomers();

}
