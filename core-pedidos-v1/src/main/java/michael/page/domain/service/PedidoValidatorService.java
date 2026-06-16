package michael.page.domain.service;

import michael.page.application.ports.out.ValidarDatosPort;
import michael.page.domain.exception.LocalException;
import michael.page.domain.model.Pedido;
import org.apache.commons.lang3.StringUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Objects;

/**
 * Regla de negocio independiente a la tecnologia
 * Valida que los pedidos cumplan ciertos requisitos para ser aceptados
 */
public class PedidoValidatorService {

  private final ValidarDatosPort validarDatosPort;

  public PedidoValidatorService(ValidarDatosPort validarDatosPort) {
    this.validarDatosPort = validarDatosPort;
  }

  public void validar(Pedido pedido) {

    if (!validarFormatoNumeroPedido(pedido.getNumeroPedido())) {
      throw new LocalException("NUMERO_PEDIDO_INVALIDO",
        "El numero de pedido solo debe contener letras o numeros");
    }

    if(pedido.getFechaEntrega().isBefore(LocalDate.now(ZoneId.of("America/Lima")))) {
      throw new LocalException("FECHA_ENTREGA_INVALIDA",
        "La fecha de entra del pedido ya paso");
    }

    if (!validarDatosPort.existeCliente(pedido.getClienteId())) {
      throw new LocalException("CLIENTE_NO_ENCONTRADO",
        "El cliente " + pedido.getClienteId() + " no existe en la BD.");
    }

    if (!validarDatosPort.existeZona(pedido.getZonaId())) {
      throw new LocalException("ZONA_INVALIDA",
        "La zona " + pedido.getZonaId() + " no existe en la BD.");
    }

    if (pedido.isRequiereRefrigeracion()) {
      if(!validarDatosPort.findZonaById(pedido.getZonaId()).isSoporteRefrigeracion()) {
        throw new LocalException("SIN_SOPORTE_REFRIGERACION",
          "La zona no cuenta con soporte de refrigeracion");
      }
    }

   }

   public static boolean validarFormatoNumeroPedido(String numeroPedido) {
     String regex = "^[A-Za-z0-9]{3}-[A-Za-z0-9]{4}$";
     if (numeroPedido == null) return false;
     return numeroPedido.matches(regex);
   }
}
