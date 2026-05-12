package idm.technic.challenge.service;

import idm.technic.challenge.entity.ApiResponse;
import idm.technic.challenge.entity.Usuario;
import idm.technic.challenge.repository.IdmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class IdmService {

  @Autowired
  private IdmRepository repository;

  public Mono<ApiResponse> findAll() {
    return repository.findAll()
      .collectList()
      .map(usuarios -> ApiResponse.builder()
        .message("Listado de usuarios")
        .response(usuarios)
        .build())
      .onErrorResume(error -> Mono.error(error));
  }

  public Mono<ApiResponse> findById(Long id) {
    return repository.findById(id)
      .map(usuario -> ApiResponse.builder()
        .message("Usuario encontrado")
        .response(usuario)
        .build())
      .switchIfEmpty(Mono.just(ApiResponse.builder()
        .message("Usuario no existe")
        .build()))
      .onErrorResume(error -> Mono.error(error));
  }

  public Mono<ApiResponse> create(Usuario usuario) {
    return repository.save(usuario)
      .map(usuarioCreado -> ApiResponse.builder()
        .message("Usuario creado")
        .response(usuarioCreado)
        .build())
      .onErrorResume(error -> Mono.error(error));
  }

  public Mono<ApiResponse> update(Usuario usuario) {
    return repository.findById(Long.valueOf(usuario.id()))
      .flatMap(originalUser ->
        repository.updateUsuario(Long.valueOf(usuario.id()), usuario.nombre(), usuario.edad(), usuario.rol())
          .flatMap(ok -> repository.findById(Long.valueOf(usuario.id())))
                                .map(resultado -> ApiResponse.builder()
                                  .message("Usuario actualizado")
                                  .response(resultado)
                                  .build()))
      .switchIfEmpty(Mono.just(ApiResponse.builder()
                    .message("Usuario no existe para actualizarse")
                    .build()));
  }

  public Mono<ApiResponse> delete(Long id) {
    return repository.deleteById(id)
      .then(Mono.just(ApiResponse.builder()
                .message("Usuario Eliminado")
                .build()))
      .onErrorResume(error -> Mono.error(error));
  }

}
