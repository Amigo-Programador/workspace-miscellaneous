package michael.page.infrastructure.adapters.out.batch.processor;

import lombok.extern.slf4j.Slf4j;
import michael.page.application.dto.ApiResponse;
import michael.page.application.ports.in.CargarPedidosUseCase;
import michael.page.domain.exception.LocalException;
import michael.page.domain.model.Pedido;
import michael.page.domain.model.TipoError;
import michael.page.domain.service.PedidoValidatorService;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import michael.page.infrastructure.adapters.out.batch.mapper.PedidoBatchMapper;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/*
 * Logica y procesamiento que el proceso batch debe seguir
 *
 */
@Slf4j
@Component
public class PedidoItemProcessor implements ItemProcessor<PedidoCsv, Pedido>, StepExecutionListener {

  private final PedidoBatchMapper pedidoBatchMapper;
  private final PedidoValidatorService pedidoValidatorService;
  private List<ApiResponse.FilaDescartada> registrosDescartados;

  public PedidoItemProcessor(PedidoBatchMapper pedidoBatchMapper,
                             PedidoValidatorService pedidoValidatorService) {
    this.pedidoBatchMapper = pedidoBatchMapper;
    this.pedidoValidatorService = pedidoValidatorService;
  }

  @Override
  public Pedido process(PedidoCsv pedidoCsv) throws Exception {
    try {
      Pedido pedido = pedidoBatchMapper.csvToDomain(pedidoCsv);
      pedidoValidatorService.validar(pedido);
      return pedido;
    } catch (LocalException le) {
      log.error("Pedido rechazado en el validador");
      String id = (pedidoCsv.getNumeroPedido() != null) ? pedidoCsv.getNumeroPedido() : "Fila sin ID";
      registrosDescartados
        .add(new ApiResponse.FilaDescartada(id, le.getTipoError(), le.getMessage()));
      return null;
    } catch (Exception ex) {
      log.error("Pedidos que no tienen formato valido");
      String id = (pedidoCsv.getNumeroPedido() != null) ? pedidoCsv.getNumeroPedido() : "Fila sin ID";
      registrosDescartados
        .add(new ApiResponse.FilaDescartada(id, "ERROR_FORMATO", ex.getMessage()));
      return null; // No se guarda en la DB
    }
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    // Inicializamos la lista cada vez que arranca el Batch
    this.registrosDescartados = new ArrayList<>();
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    stepExecution.getJobExecution().getExecutionContext().put("listaErrores", this.registrosDescartados);
    return null;
  }

}
