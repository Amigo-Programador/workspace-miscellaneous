package amigo.programador.hexagonal.infrastructure.adapter.in.mapper;

import amigo.programador.hexagonal.domain.model.Customer;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.CustomerRequest;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.CustomerResponse;
import org.springframework.stereotype.Component;

@Component
public class CustomerWebMapper {

  public CustomerResponse toResponse(Customer customer) {
    return new CustomerResponse(
      customer.getId(),
      customer.getName(),
      customer.getAge(),
      customer.getDni(),
      customer.getBirthday());
  }

  public Customer toDomain(CustomerResponse customerResponse) {
    return Customer.builder()
      .id(customerResponse.id())
      .name(customerResponse.name())
      .age(customerResponse.age())
      .birthday(customerResponse.birthday())
      .build();
  }

  public CustomerRequest toRequest(Customer customer) {
    return new CustomerRequest(
      customer.getName(),
      customer.getAge(),
      customer.getDni(),
      customer.getBirthday());
  }

  public Customer fromRequest(CustomerRequest customerRequest) {
    return Customer.builder()
      .name(customerRequest.name())
      .age(customerRequest.age())
      .dni(customerRequest.dni())
      .birthday(customerRequest.birthday())
      .build();
  }

}
