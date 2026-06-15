package michael.page.infrastructure.adapters.out.batch.reader;

import michael.page.infrastructure.adapters.out.batch.dto.PedidoCsv;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.FileSystemResource;

/*
 * Encargado de abrir y procesar el archiv csv
 */

public class PedidoCsvFlatItemReader extends FlatFileItemReader<PedidoCsv> {

  public PedidoCsvFlatItemReader(String filePath) {
    setResource(new FileSystemResource(filePath));
    setLineMapper(new DefaultLineMapper<>() {{

      setLineTokenizer(new DelimitedLineTokenizer(",") {{
        setNames("numeroPedido", "clienteId", "fechaEntrega", "estado", "zonaEntrega", "requiereRefrigeracion");
      }});

      setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
        setTargetType(PedidoCsv.class);
      }});

    }});
  }

}
