package idm.technic.challenge.repository;

import idm.technic.challenge.entity.Usuario;
import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;

public interface IdmRepository extends R2dbcRepository<Usuario, Long> {

  @Modifying
  @Query("UPDATE usuario SET nombre = :nombre, edad = :edad, rol = :rol WHERE id = :id")
  Mono<Integer> updateUsuario(Long id, String nombre, int edad, String rol);
}
