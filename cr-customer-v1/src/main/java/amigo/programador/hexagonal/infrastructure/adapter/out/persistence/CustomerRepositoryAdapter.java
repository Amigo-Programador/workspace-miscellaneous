package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.application.port.out.CustomerRepositoryPort;
import amigo.programador.hexagonal.domain.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

  private final CustomerMongoRepository customerMongoRepository;
  private final CustomerMapper customerMapper;

  public CustomerRepositoryAdapter(CustomerMongoRepository customerMongoRepository,
                                   CustomerMapper customerMapper) {
    this.customerMongoRepository = customerMongoRepository;
    this.customerMapper = customerMapper;
  }

  @Override
  public Flux<Customer> findAll() {
    return customerMongoRepository.findAll()
      .map(customerMapper::toDomain);
  }

  @Override
  public Mono<Customer> findById(String id) {
    return customerMongoRepository.findById(id)
      .map(customerMapper::toDomain);
  }

  @Override
  public Mono<Customer> save(Customer customer) {
    CustomerDocument c = customerMapper.fromDomain(customer);
    return customerMongoRepository.save(c)
      .map(customerMapper::toDomain);
  }

  @Override
  public Mono<Boolean> validateDuplicateDni(String dni) {
    return customerMongoRepository.existsByDni(dni);
  }
}