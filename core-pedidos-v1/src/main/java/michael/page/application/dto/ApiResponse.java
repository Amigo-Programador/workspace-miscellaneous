package michael.page.application.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ApiResponse {
  private String mensaje;
  private long totalProcesados;
  private long totalGuardados;
  private int pedidosConError;
  private Map<String, Long> erroresPorTipo;
  private List<FilaDescartada> detallesDescartados;

  @Getter
  @Setter
  public static class FilaDescartada {
    private String numeroPedido;
    private String tipoError;
    private String motivoError;

    public FilaDescartada(String numeroPedido,
                          String tipoError,
                          String motivoError) {
      this.numeroPedido = numeroPedido;
      this.tipoError = tipoError;
      this.motivoError = motivoError;
    }
  }
}
