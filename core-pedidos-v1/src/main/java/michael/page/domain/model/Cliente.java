package michael.page.domain.model;

import java.util.UUID;

public class Cliente {

  private UUID id;
  private boolean activo;

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }
}
