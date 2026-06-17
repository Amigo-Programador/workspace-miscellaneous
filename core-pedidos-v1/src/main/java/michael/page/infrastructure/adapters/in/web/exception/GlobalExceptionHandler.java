package michael.page.infrastructure.adapters.in.web.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGlobalException(Exception ex) {
    String correlationId = UUID.randomUUID().toString();

    // Aquí personalizas el código según la excepción
    ErrorResponse error = new ErrorResponse(
      "INTERNAL_SERVER_ERROR",
      ex.getMessage(),
      List.of("Ocurrió un error inesperado al procesar la solicitud"),
      correlationId
    );

    return ResponseEntity.internalServerError().body(error);
  }
}
