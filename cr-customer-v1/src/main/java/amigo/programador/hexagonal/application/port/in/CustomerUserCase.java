package amigo.programador.hexagonal.application.port.in;

import amigo.programador.hexagonal.domain.model.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerUserCase {

  public Mono<Customer> createCustomer(Customer customer);

  public Mono<Customer> findCustomerById(String id);

  public Flux<Customer> getAllCustomers();

}
