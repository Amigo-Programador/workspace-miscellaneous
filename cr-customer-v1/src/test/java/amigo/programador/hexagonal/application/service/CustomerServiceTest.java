package amigo.programador.hexagonal.application.service;

import amigo.programador.hexagonal.application.port.out.CustomerRepositoryPort;
import amigo.programador.hexagonal.domain.model.Customer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

  @Mock
  private CustomerRepositoryPort customerRepositoryPort;

  @InjectMocks
  private CustomerService customerService;

  private Customer customer;

  @BeforeEach
  void setUp() {
    customer = Customer.builder()
      .id("1").name("Renzo").dni("12345678")
      .age(30L).birthday(LocalDate.of(1995, 5, 15))
      .build();
  }

  @Test
  void createCustomer_whenDniDoesNotExist_shouldSaveAndReturnCustomer() {
    when(customerRepositoryPort.validateDuplicateDni("12345678")).thenReturn(Mono.just(false));
    when(customerRepositoryPort.save(customer)).thenReturn(Mono.just(customer));

    StepVerifier.create(customerService.createCustomer(customer))
      .expectNext(customer)
      .verifyComplete();

    verify(customerRepositoryPort).save(customer);
  }

  @Test
  void createCustomer_whenDniExists_shouldFailAndNotSave() {
    when(customerRepositoryPort.validateDuplicateDni("12345678")).thenReturn(Mono.just(true));

    StepVerifier.create(customerService.createCustomer(customer))
      .expectErrorSatisfies(ex -> assertThat(ex)
          .isInstanceOf(IllegalArgumentException.class)
          .hasMessage("El DNI 12345678 ya se encuentra registrado"))
      .verify();

    verify(customerRepositoryPort, Mockito.never()).save(any());
  }

}