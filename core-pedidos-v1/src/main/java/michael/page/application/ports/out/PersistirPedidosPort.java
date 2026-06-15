package michael.page.application.ports.out;

import michael.page.domain.model.Pedido;

import java.util.List;


/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Procesa mediante un batch los archivos CSV
 */
public interface PersistirPedidosPort {

  public void guardarEnLote(List<Pedido> pedidos);

}
