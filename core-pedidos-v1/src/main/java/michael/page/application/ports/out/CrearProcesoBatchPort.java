package michael.page.application.ports.out;


import michael.page.application.dto.ApiResponse;

/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Procesa mediante un batch los archivos CSV
 */
public interface CrearProcesoBatchPort {

  public ApiResponse startJob(String pathFile);

}
