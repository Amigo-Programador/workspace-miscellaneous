package michael.page.infrastructure.adapters.out.batch.dto;

import lombok.Data;

@Data
public class PedidoCsv {
  private String numeroPedido;
  private String clienteId;
  private String fechaEntrega;
  private String estado;
  private String zonaEntrega;
  private String requiereRefrigeracion;


}
