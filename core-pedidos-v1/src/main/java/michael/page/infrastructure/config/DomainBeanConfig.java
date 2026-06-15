package michael.page.infrastructure.config;

import michael.page.application.ports.out.ValidarDatosPort;
import michael.page.domain.service.PedidoValidatorService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomainBeanConfig {
  @Bean
  public PedidoValidatorService pedidoValidatorService(ValidarDatosPort validarDatosPort) {
    return new PedidoValidatorService(validarDatosPort);
  }
}
