package amigo.programador.hexagonal.infrastructure.adapter.out.persistence;

import amigo.programador.hexagonal.domain.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerDocumentMapper {

  public Customer toDomain(CustomerDocument customerDocument) {
    return Customer.builder()
      .id(customerDocument.getId())
      .name(customerDocument.getName())
      .dni(customerDocument.getDni())
      .age(customerDocument.getAge())
      .birthday(customerDocument.getBirthday())
      .build();
  }

  public CustomerDocument fromDomain(Customer customer) {
    return CustomerDocument.builder()
      .id(customer.getId())
      .name(customer.getName())
      .age(customer.getAge())
      .dni(customer.getDni())
      .birthday(customer.getBirthday())
      .build();
  }

}