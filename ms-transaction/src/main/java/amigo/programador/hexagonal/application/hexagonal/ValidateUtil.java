package amigo.programador.hexagonal.application.hexagonal;

import reactor.core.publisher.Mono;

import java.util.function.Function;

public class ValidateUtil {

  public static <T, K> Mono<K> validateDuplicateLambda(T code, Function<T, Mono<K>> function) {

    try {
      return function.apply(code);
    } catch (Exception e) {
      throw new BusinessRuleException("Se encontro duplicados");
    }

  }
}
