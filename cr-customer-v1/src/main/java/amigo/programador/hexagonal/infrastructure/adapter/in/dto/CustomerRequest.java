package amigo.programador.hexagonal.infrastructure.adapter.in.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record CustomerRequest (
  @NotBlank
  String name,

  @NotNull
  @Positive(message = "La edad debe ser mayor a 0")
  Long age,

  @NotBlank
  @Pattern(regexp = "^[0-9]{8}$", message = "El DNI debe contener exactamente 8 dígitos numéricos")
  String dni,

  @NotNull
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  LocalDate birthday
) {}
