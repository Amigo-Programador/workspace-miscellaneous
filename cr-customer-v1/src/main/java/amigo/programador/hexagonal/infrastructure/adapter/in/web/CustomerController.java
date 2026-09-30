package amigo.programador.hexagonal.infrastructure.adapter.in.web;

import amigo.programador.hexagonal.application.service.CustomerService;
import amigo.programador.hexagonal.domain.model.Customer;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.CustomerRequest;
import amigo.programador.hexagonal.infrastructure.adapter.in.dto.CustomerResponse;
import amigo.programador.hexagonal.infrastructure.adapter.in.mapper.CustomerWebMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Tag(name = "Customer API", description = "Endpoints para la gestión de clientes")
@RestController
@RequestMapping("/customer")
public class CustomerController {

  private final CustomerService customerService;
  private final CustomerWebMapper customerWebMapper;

  public CustomerController(CustomerService customerService,
                            CustomerWebMapper customerWebMapper) {
    this.customerService = customerService;
    this.customerWebMapper = customerWebMapper;
  }

  @Operation(summary = "Obtener todos los clientes")
  @GetMapping("/getAll")
  public Flux<CustomerResponse> getAllCustomers() {
    return customerService.getAllCustomers()
      .map(customerWebMapper::toResponse);
  }

  @Operation(summary = "Buscar cliente por ID")
  @GetMapping("/find/{id}")
  public Mono<CustomerResponse> getCustomerById(@PathVariable String id) {
    return customerService.findCustomerById(id)
      .map(customerWebMapper::toResponse);
  }

  @Operation(summary = "Crear un nuevo cliente")
  @PostMapping("/create")
  public Mono<CustomerResponse> createCustomer(@Valid @RequestBody CustomerRequest customerRequest) {
    Customer customer = customerWebMapper.fromRequest(customerRequest);

    return customerService.createCustomer(customer)
      .map(customerWebMapper::toResponse);
  }

}
