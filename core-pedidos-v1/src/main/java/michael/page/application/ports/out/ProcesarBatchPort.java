package michael.page.application.ports.out;


/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Procesa mediante un batch los archivos CSV
 */
public interface ProcesarBatchPort {

  public void startJob(String pathFile);

}
