package michael.page.infrastructure.adapters.out.jpa;

import michael.page.application.ports.out.ValidarDatosPort;
import michael.page.domain.exception.LocalException;
import michael.page.domain.model.Zona;
import michael.page.infrastructure.adapters.out.jpa.mapper.ZonaEntityMapper;
import michael.page.infrastructure.adapters.out.jpa.repository.ClienteJpaRepository;
import michael.page.infrastructure.adapters.out.jpa.repository.ZonaJpaRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class ValidarDatosAdapter implements ValidarDatosPort {

  private final ClienteJpaRepository clienteRepository;
  private final ZonaJpaRepository zonaRepository;
  private final ZonaEntityMapper zonaEntityMapper;

  public ValidarDatosAdapter(ClienteJpaRepository clienteRepository,
                             ZonaJpaRepository zonaRepository,
                             ZonaEntityMapper zonaEntityMapper) {
    this.clienteRepository = clienteRepository;
    this.zonaRepository = zonaRepository;
    this.zonaEntityMapper = zonaEntityMapper;
  }

  @Override
  @Cacheable(value = "clientesCache")
  public boolean existeCliente(String clienteId) {
    return clienteRepository.existsById(clienteId);
  }

  @Override
  @Cacheable(value = "zonasCache")
  public boolean existeZona(String zonaId) {
    return zonaRepository.existsById(zonaId);
  }

  @Override
  @Cacheable(value = "zonasSporteRefrigeracionCache")
  public Zona findZonaById(String zonaId) {
    return zonaRepository.findById(zonaId)
      .map(zonaEntityMapper::toZonaDomain)
      .orElseThrow(() -> new LocalException("ZONA_INVALIDA",
        "Zona no encontrada: " + zonaId));
  }
}
