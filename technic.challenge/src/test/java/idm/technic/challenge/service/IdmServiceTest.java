package idm.technic.challenge.service;

import idm.technic.challenge.entity.ApiResponse;
import idm.technic.challenge.entity.Usuario;
import idm.technic.challenge.repository.IdmRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class IdmServiceTest {

  private Usuario usuarioMock;
  private ApiResponse responseMock;

  @Mock
  private IdmRepository idmRepository;

  @InjectMocks
  private IdmService idmService;

  @Test
  void testWhenCreateUsuarioIsOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    responseMock = ApiResponse.builder()
      .message("Usuario creado")
      .response(usuarioMock)
      .build();

    when(idmRepository.save(any(Usuario.class))).thenReturn(Mono.just(usuarioMock));

    Mono<ApiResponse> result = idmService.create(usuarioMock);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Usuario creado");
        assert apiResponse.getResponse().equals(usuarioMock);
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenFindAllIsOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    responseMock = ApiResponse.builder()
      .message("Listado de usuarios")
      .response(List.of(usuarioMock))
      .build();

    when(idmRepository.findAll()).thenReturn(Flux.just(usuarioMock));

    Mono<ApiResponse> result = idmService.findAll();

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Listado de usuarios");
        assert apiResponse.getResponse().equals(List.of(usuarioMock));
        return true;
      })
      .verifyComplete();
  }

  @Test
  void testWhenUpdateUsuarioIsOk() {
    usuarioMock = new Usuario(1L, "Hyperx", 21, "secret");
    Usuario usuarioMockUpdated = new Usuario(1L, "Logitech", 33, "mouse");

    responseMock = ApiResponse.builder()
      .message("Usuario actualizado")
      .response(usuarioMock)
      .build();

    when(idmRepository.findById(any(Long.class)))
      .thenReturn(Mono.just(usuarioMock))
      .thenReturn(Mono.just(usuarioMockUpdated));
    when(idmRepository.updateUsuario(any(Long.class), any(String.class), any(Integer.class), any(String.class)))
      .thenReturn(Mono.just(1));

    Mono<ApiResponse> result = idmService.update(usuarioMockUpdated);

    StepVerifier.create(result)
      .expectNextMatches(apiResponse -> {
        assert apiResponse.getMessage().equals("Usuario actualizado");
        assert apiResponse.getResponse().equals(usuarioMockUpdated);
        return true;
      })
      .verifyComplete();
  }
}
