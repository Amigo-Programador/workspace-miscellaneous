package michael.page.application.ports.out;

/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Herramienta necesaria para generar el Hash
 * Compara con la base de datos si existe y sino lo registra
 */
public interface CargaIdempotenciaRepositoryPort {

  public boolean existFile(String idempotencyKey, String archivoHash);

  public void registerFile(String idempotencyKey, String archivoHash);
}
