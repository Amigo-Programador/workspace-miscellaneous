package michael.page.application.usecases;

import michael.page.application.ports.in.CargarPedidosUseCase;
import michael.page.application.ports.out.ProcesarBatchPort;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;


/**
 * Tarea operativa del negocio que orquesta el inicio del procese bash de carga de archivos.
 *  1. Recibir el archivo csv.
 *  2. Procesar bloques de registros usando un job bash.
 *  3. Persirtir los pedidos validos en la base de datos en bloques.
 */
@Service
public class CargarPedidosUseCaseImpl implements CargarPedidosUseCase {

  private final ProcesarBatchPort procesarBatchPort;

  public CargarPedidosUseCaseImpl(ProcesarBatchPort procesarBatchPort) {
    this.procesarBatchPort = procesarBatchPort;
  }

  @Override
  public void procesarPedidos(byte[] fileBytes) { //tiene que retornar algo
    try {
      Path tempFile = Files.createTempFile("pedidos_upload_", ".csv");
      Files.write(tempFile, fileBytes);

      String pathFile = tempFile.toAbsolutePath().toString();
      procesarBatchPort.startJob(pathFile);
    } catch (Exception ex) {

    }

  }

}