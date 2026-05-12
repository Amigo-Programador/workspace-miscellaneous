package idm.technic.challenge.interceptor;

import idm.technic.challenge.entity.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class IdmInterceptor {

  @ExceptionHandler({Throwable.class})
  protected ApiResponse handAllExceptions(Throwable ex) {
    log.error(String.format("Interceptor Idm Exception: %s", ex));
    return ApiResponse.builder()
      .message("Error inesperado!")
      .build();
  }
}
