package michael.page.infrastructure.adapters.out.batch;

import michael.page.application.dto.ApiResponse;
import michael.page.application.ports.out.CrearProcesoBatchPort;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CrearProcesoBatchAdapter implements CrearProcesoBatchPort {

  private final JobLauncher jobLauncher;
  private final Job procesarPedidosJob;

  public CrearProcesoBatchAdapter(JobLauncher jobLauncher, Job procesarPedidoJob) {
    this.jobLauncher = jobLauncher;
    this.procesarPedidosJob = procesarPedidoJob;
  }

  @Override
  public ApiResponse startJob(String filePath) {
    try {
      JobExecution execution = jobLauncher.run(procesarPedidosJob, new JobParametersBuilder()
        .addString("rutaArchivo", filePath)
        .addLong("timestamp", System.currentTimeMillis()) // Asegura que el Job sea único por ejecución
        .toJobParameters());

      long totalExitosos = execution.getStepExecutions().iterator().next().getWriteCount();

      List<ApiResponse.FilaDescartada> pedidosConError =
        (List<ApiResponse.FilaDescartada>) execution.getExecutionContext().get("listaErrores");

      if (pedidosConError == null) {
        pedidosConError = new ArrayList<>();
      }

      Map<String, Long> erroresPorTipo = pedidosConError.stream()
        .collect(Collectors.groupingBy(ApiResponse.FilaDescartada::getTipoError, Collectors.counting()));

      ApiResponse response = new ApiResponse();
      response.setMensaje("Procesamiento de pedidos en batch finalizado.");
      response.setTotalGuardados(totalExitosos);
      response.setPedidosConError(pedidosConError.size());
      response.setTotalProcesados(totalExitosos + pedidosConError.size());
      response.setErroresPorTipo(erroresPorTipo);
      response.setDetallesDescartados(pedidosConError);

      return response;
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
