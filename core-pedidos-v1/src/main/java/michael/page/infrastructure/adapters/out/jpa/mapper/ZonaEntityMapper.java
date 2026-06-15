package michael.page.infrastructure.adapters.out.jpa.mapper;

import michael.page.domain.model.Zona;
import michael.page.infrastructure.adapters.out.jpa.entity.ZonaEntity;
import org.springframework.stereotype.Component;

@Component
public class ZonaEntityMapper {

  public Zona toZonaDomain(ZonaEntity zonaEntity) {
    return new Zona(zonaEntity.getId(), zonaEntity.isSoporteRefrigeracion());
  }

}
