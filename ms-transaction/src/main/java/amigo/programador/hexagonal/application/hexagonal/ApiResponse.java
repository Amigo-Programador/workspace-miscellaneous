package amigo.programador.hexagonal.application.hexagonal;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiResponse<T> {
  private Boolean success;
  private String message;
  private T response;
  private LocalDateTime timestamp;

  public static <T> ApiResponse<T> success(String message, T data) {
    return ApiResponse.<T>builder()
      .success(true)
      .message(message)
      .response(data)
      .timestamp(LocalDateTime.now())
      .build();
  }

  public static <T> ApiResponse<T> error(String message) {
    return ApiResponse.<T>builder()
      .success(false)
      .message(message)
      .response(null)
      .timestamp(LocalDateTime.now())
      .build();
  }
}
