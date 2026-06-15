package michael.page.infrastructure.adapters.out.batch.config;

import michael.page.domain.model.Pedido;
import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import michael.page.infrastructure.adapters.out.batch.processor.PedidoItemProcessor;
import michael.page.infrastructure.adapters.out.batch.reader.PedidoCsvFlatItemReader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

/*
 * Adaptador de ejecuciones planificadas o archivos masivos
 * Orquesta el proceso batch
 */
@Configuration
public class PedidoBatchConfig {

  @Bean
  public Job procesarPedidosJob(JobRepository jobRepository, Step step) {
    return new JobBuilder("procesarPedidosJob", jobRepository)
      .start(step)
      .build();
  }

  @Bean
  public Step step(JobRepository jobRepository,
                   PlatformTransactionManager transactionManager,
                   PedidoItemProcessor pedidoItemProcessor) {
    return new StepBuilder("csvStep", jobRepository)
      .<PedidoCsv, Pedido>chunk(100, transactionManager)
      .reader(new PedidoCsvFlatItemReader("ruta/a/tu/archivo.csv"))
      .processor(pedidoItemProcessor)
      .writer(list -> {})
      .build();
  }

}
