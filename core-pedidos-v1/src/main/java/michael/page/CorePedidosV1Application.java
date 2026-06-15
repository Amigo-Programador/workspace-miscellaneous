package michael.page;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class CorePedidosV1Application {

	public static void main(String[] args) {
		SpringApplication.run(CorePedidosV1Application.class, args);
	}

}
