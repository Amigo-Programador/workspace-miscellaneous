package michael.page.infrastructure.adapters.out.batch;

import michael.page.application.ports.out.ProcesarBashPort;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Component;

@Component
public class ProcesarBatchAdapter implements ProcesarBashPort {

  private final JobLauncher jobLauncher;
  private final Job procesarPedidoJob;

  public ProcesarBatchAdapter(JobLauncher jobLauncher, Job procesarPedidoJob) {
    this.jobLauncher = jobLauncher;
    this.procesarPedidoJob = procesarPedidoJob;
  }

  @Override
  public void startJob(String filePath) {
    try {
      jobLauncher.run(procesarPedidoJob, new JobParametersBuilder()
        .addString("rutaArchivo", filePath)
        .addLong("timestamp", System.currentTimeMillis()) // Asegura que el Job sea único por ejecución
        .toJobParameters());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
