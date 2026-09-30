package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.application.port.out.CustomerRepositoryPort;
import amigo.programador.hexagonal.domain.model.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class CustomerRepositoryAdapter implements CustomerRepositoryPort {

  private final CustomerMongoRepository customerMongoRepository;
  private final CustomerDocumentMapper customerDocumentMapper;

  public CustomerRepositoryAdapter(CustomerMongoRepository customerMongoRepository,
                                   CustomerDocumentMapper customerDocumentMapper) {
    this.customerMongoRepository = customerMongoRepository;
    this.customerDocumentMapper = customerDocumentMapper;
  }

  @Override
  public Flux<Customer> findAll() {
    return customerMongoRepository.findAll()
      .map(customerDocumentMapper::toDomain);
  }

  @Override
  public Mono<Customer> findById(String id) {
    return customerMongoRepository.findById(id)
      .map(customerDocumentMapper::toDomain);
  }

  @Override
  public Mono<Customer> save(Customer customer) {
    CustomerDocument c = customerDocumentMapper.fromDomain(customer);
    return customerMongoRepository.save(c)
      .map(customerDocumentMapper::toDomain);
  }

  @Override
  public Mono<Boolean> validateDuplicateDni(String dni) {
    return customerMongoRepository.existsByDni(dni);
  }
}