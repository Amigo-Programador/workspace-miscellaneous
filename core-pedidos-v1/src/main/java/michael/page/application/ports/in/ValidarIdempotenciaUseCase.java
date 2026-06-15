package michael.page.application.ports.in;

/**
 * Expone lo que tu sistema ofrece al mundo exterior
 */
public interface ValidarIdempotenciaUseCase {

  void registrarSiEsNuevo(String idempotencyKey, byte[] fileBytes);
}
