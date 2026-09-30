package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CustomerResponse (
  String id,
  String name,
  Long age,
  String dni,
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  LocalDate birthday
) {}
