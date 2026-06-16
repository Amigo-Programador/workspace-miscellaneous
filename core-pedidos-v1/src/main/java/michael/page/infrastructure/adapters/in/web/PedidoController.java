package michael.page.infrastructure.adapters.in.web;

import michael.page.application.dto.ApiResponse;
import michael.page.application.ports.in.CargarPedidosUseCase;
import michael.page.application.ports.in.ValidarIdempotenciaUseCase;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Adaptador de peticiones HTTP
 * Aqui exponemos los endpoints del API
 */
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final ValidarIdempotenciaUseCase validarIdempotenciaUseCase;
  private final CargarPedidosUseCase cargarPedidosUseCase;

  public PedidoController(CargarPedidosUseCase cargarPedidosUseCase,
                          ValidarIdempotenciaUseCase validarIdempotenciaUseCase) {
    this.cargarPedidosUseCase = cargarPedidosUseCase;
    this.validarIdempotenciaUseCase = validarIdempotenciaUseCase;
  }

  @PostMapping(value = "/cargar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<ApiResponse> cargarPedidos(
    @RequestHeader("Idempotency-Key") String idempotencyKey,
    @RequestParam("file") MultipartFile file) throws IOException {

    byte[] fileBytes = file.getBytes();

    // Valida idempotencia del archivo
    validarIdempotenciaUseCase.registrarSiEsNuevo(idempotencyKey, fileBytes);

    // Cargar productos con el job batch
    ApiResponse response = cargarPedidosUseCase.procesarPedidos(fileBytes);


    return ResponseEntity.ok(response);
  }


}
