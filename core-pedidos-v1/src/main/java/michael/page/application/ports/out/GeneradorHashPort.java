package michael.page.application.ports.out;

/**
 * Define lo que la Capa de Aplicacion exige a la Infraestructura
 * Herramienta necesaria para generar el Hash
 */
public interface GeneradorHashPort {

  public String generarHash(byte[] data);

}
