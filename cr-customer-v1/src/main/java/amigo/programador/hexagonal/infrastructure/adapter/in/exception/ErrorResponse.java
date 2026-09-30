package amigo.programador.hexagonal.infrastructure.adapter.in.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ErrorResponse(
  LocalDateTime dateTime,
  Integer status,
  String error,
  String message
) {}