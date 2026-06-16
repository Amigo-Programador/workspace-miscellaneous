package michael.page.application.usecases;

import michael.page.application.ports.in.ValidarIdempotenciaUseCase;
import michael.page.application.ports.out.CargaIdempotenciaRepositoryPort;
import michael.page.application.ports.out.GeneradorHashPort;
import michael.page.domain.exception.LocalException;
import org.springframework.stereotype.Service;

/**
 * Tarea operativa del negocio que controlo los nuevos archivos CSV
 *  1. Calcular el hash SHA-256 del archivo.
 *  2. Validar combinacion Idempotency-Key + Hash existe en base de datos.
 *  3. Si existe lanzar una excepcion, Si NO existe registrarlo en la base de datos.
 *  4. Solo permitir los archivos CSV que pasen la validacion.
 */
@Service
public class ValidarIdempotenciaUseCaseImpl implements ValidarIdempotenciaUseCase {

  private final GeneradorHashPort generadorHashPort;
  private final CargaIdempotenciaRepositoryPort cargaIdempotenciaRepositoryPort;

  public ValidarIdempotenciaUseCaseImpl(GeneradorHashPort generadorHashPort,
                                        CargaIdempotenciaRepositoryPort cargaIdempotenciaRepositoryPort) {
    this.generadorHashPort = generadorHashPort;
    this.cargaIdempotenciaRepositoryPort = cargaIdempotenciaRepositoryPort;
  }

  @Override
  public void registrarSiEsNuevo(String idempotencyKey, byte[] fileBytes) {
    String archivoHash = generadorHashPort.generarHash(fileBytes);

    boolean exist = cargaIdempotenciaRepositoryPort.existFile(idempotencyKey, archivoHash);

    if (exist) {
      throw new LocalException("DUPLICADO", "El archivo ya fue procesado anteriormente");
    }

    cargaIdempotenciaRepositoryPort.registerFile(idempotencyKey, archivoHash);
  }
}
