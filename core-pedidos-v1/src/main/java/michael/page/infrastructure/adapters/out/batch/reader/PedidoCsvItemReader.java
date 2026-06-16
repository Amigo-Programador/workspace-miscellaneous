package michael.page.infrastructure.adapters.out.batch.reader;

import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.FileSystemResource;

/**
 * Encargado de abrir e iniciar el batch con los datos del archivo
 */

public class PedidoCsvItemReader extends FlatFileItemReader<PedidoCsv> {

  public PedidoCsvItemReader(String filePath) {
    setResource(new FileSystemResource(filePath));
//    setLinesToSkip(1); // Saltamos la linea de los headers, se comenta si el csv viene con datos puros

    setLineMapper(new DefaultLineMapper<>() {{
      setLineTokenizer(new DelimitedLineTokenizer(",") {{
        setNames("numeroPedido", "clienteId", "fechaEntrega", "estado", "zonaEntrega", "requiereRefrigeracion");
      }});

      setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
        setTargetType(PedidoCsv.class); // Guarda lo leido en un objeto PedidoCsv
      }});
    }});
  }

}
