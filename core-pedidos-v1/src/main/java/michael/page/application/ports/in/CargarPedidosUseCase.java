package michael.page.application.ports.in;

import michael.page.domain.model.Pedido;

/**
 * Expone lo que tu sistema ofrece al mundo exterior
 */
public interface CargarPedidosUseCase {

  void procesarPedidos(byte[] fileBytes);

}
