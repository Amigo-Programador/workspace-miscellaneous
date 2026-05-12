package idm.technic.challenge.controller;

import idm.technic.challenge.entity.ApiResponse;
import idm.technic.challenge.entity.Usuario;
import idm.technic.challenge.service.IdmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class IdmControllerTest {

  private Usuario usuarioMock;
  private ApiResponse responseMock;

  @Mock
  private IdmService idmService;

  @InjectMocks
  private IdmController idmController;

  @Test
  void testWhenCreateUsuarioIsOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    responseMock = ApiResponse.builder()
      .message("Usuario creado")
      .response(usuarioMock)
      .build();

    when(idmService.create(any(Usuario.class)))
      .thenReturn(Mono.just(responseMock));

    Mono<ApiResponse> result = idmController.create(usuarioMock);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Usuario creado");
        assert apiResponse.getResponse().equals(usuarioMock);
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenCreateUsuarioResultError() {
    usuarioMock = new Usuario(1L, null, 15, null);
    responseMock = ApiResponse.builder()
      .message("Error inesperado!")
      .build();

    when(idmService.create(any(Usuario.class)))
      .thenReturn(Mono.just(responseMock));

    Mono<ApiResponse> result = idmController.create(usuarioMock);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Error inesperado!");
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenListUsuariosOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    responseMock = ApiResponse.builder()
      .message("Listado de usuarios")
      .response(List.of(usuarioMock))
      .build();

    when(idmService.findAll())
      .thenReturn(Mono.just(responseMock));

    Mono<ApiResponse> result = idmController.findAll();

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Listado de usuarios");
        assert apiResponse.getResponse().equals(List.of(usuarioMock));
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenUpdateUsuariosOk() {
    usuarioMock = new Usuario(2L, "Gina", 27, "secret");
    responseMock = ApiResponse.builder()
      .message("Usuario actualizado")
      .response(usuarioMock)
      .build();

    when(idmService.update(any(Usuario.class)))
      .thenReturn(Mono.just(responseMock));

    Mono<ApiResponse> result = idmController.update(usuarioMock);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Usuario actualizado");
        assert apiResponse.getResponse().equals(usuarioMock);
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenFindUsuarioIsOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    responseMock = ApiResponse.builder()
      .message("Usuario encontrado")
      .response(usuarioMock)
      .build();

    when(idmService.findById(any(Long.class)))
      .thenReturn(Mono.just(responseMock));

    Mono<ApiResponse> result = idmController.findById(1L);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Usuario encontrado");
        assert apiResponse.getResponse().equals(usuarioMock);
        return true;
      })
      .verifyComplete();
  }

}
