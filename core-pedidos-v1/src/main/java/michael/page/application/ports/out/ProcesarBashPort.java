package michael.page.application.ports.out;

import michael.page.domain.model.Pedido;


/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Procesa mediante un batch los archivos CSV
 */
public interface ProcesarBashPort {

  public void startJob(String pathFile);

}
