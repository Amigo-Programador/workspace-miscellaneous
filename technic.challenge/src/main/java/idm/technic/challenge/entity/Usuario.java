package idm.technic.challenge.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")
public record Usuario(@Id Long id,
                      @NotBlank String nombre,
                      @NotNull int edad,
                      @NotBlank String rol) {

}

