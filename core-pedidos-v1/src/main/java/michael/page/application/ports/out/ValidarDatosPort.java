package michael.page.application.ports.out;

import michael.page.domain.model.Zona;

public interface ValidarDatosPort {
  public boolean existeCliente(String clienteId);
  public boolean existeZona(String zonaId);
  public Zona findZonaById(String zonaId);
}
