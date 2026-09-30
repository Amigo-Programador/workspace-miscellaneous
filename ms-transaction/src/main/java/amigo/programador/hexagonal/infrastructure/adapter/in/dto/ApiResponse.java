package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse<T> {
  private String message;
  private T response;

  public static <T> ApiResponse<T> success(String message, T response) {
    return ApiResponse.<T>builder()
      .message(message)
      .response(response)
      .build();
  }
}
