package michael.page.infrastructure.adapters.out.batch.config;

import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import michael.page.infrastructure.adapters.out.batch.processor.PedidoItemProcessor;
import michael.page.infrastructure.adapters.out.batch.reader.PedidoCsvItemReader;
import michael.page.infrastructure.adapters.out.batch.writer.PedidoItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * Adaptador de ejecuciones planificadas o archivos masivos
 * Orquesta el proceso batch
 */
@Configuration
public class PedidoBatchConfig {

  @Value("${pedidos.batch.size:10}")
  private int batchSize;

  @Bean
  public Job procesarPedidosJob(JobRepository jobRepository, Step step) {
    return new JobBuilder("procesarPedidosJob", jobRepository)
      .start(step)
      .build();
  }

  @Bean
  public Step step(JobRepository jobRepository,
                   PlatformTransactionManager transactionManager,
                   PedidoItemProcessor pedidoItemProcessor,
                   PedidoItemWriter pedidoItemWriter,
                   PedidoCsvItemReader pedidoCsvReader) {
    return new StepBuilder("csvStep", jobRepository)
      .<PedidoCsv, Pedido>chunk(batchSize, transactionManager)
      .reader(pedidoCsvReader)
      .processor(pedidoItemProcessor)
      .writer(pedidoItemWriter)
      .listener(pedidoItemProcessor)
      .build();
  }

  @Bean
  @StepScope
  public PedidoCsvItemReader pedidoCsvReader(@Value("#{jobParameters['rutaArchivo']}") String rutaArchivo) {

    if (rutaArchivo == null || rutaArchivo.isEmpty()) {
      throw new IllegalStateException("La ruta del archivo no puede ser nula. ¡Verifica el JobParameters!");
    }

    return new PedidoCsvItemReader(rutaArchivo);
  }
}
