package amigo.programador.hexagonal.application.service;

import amigo.programador.hexagonal.application.port.in.CustomerUseCase;
import amigo.programador.hexagonal.application.port.out.CustomerRepositoryPort;
import amigo.programador.hexagonal.domain.model.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerService implements CustomerUseCase {

  private final CustomerRepositoryPort customerRepositoryPort;

  public CustomerService(CustomerRepositoryPort customerRepositoryPort) {
    this.customerRepositoryPort = customerRepositoryPort;
  }

  @Override
  public Mono<Customer> createCustomer(Customer customer) {
    return customerRepositoryPort.validateDuplicateDni(customer.getDni())
        .flatMap(exists -> {
          if (Boolean.TRUE.equals(exists)) {
            return Mono.error(new IllegalArgumentException("El DNI " + customer.getDni() + " ya se encuentra registrado"));
          }
          return customerRepositoryPort.save(customer);
        });
  }

  @Override
  public Mono<Customer> findCustomerById(String id) {
    return customerRepositoryPort.findById(id)
        .switchIfEmpty(Mono.error(new IllegalArgumentException("Cliente no encontrado con id: " + id)));
  }

  @Override
  public Flux<Customer> getAllCustomers() {
    return customerRepositoryPort.findAll();
  }
}