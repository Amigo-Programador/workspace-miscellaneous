package amigo.programador.hexagonal.infrastructure.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.stereotype.Component;

@Configuration
public class BeanInitializerConfig {

//  @Autowired
//  public void configureMongoConverter(MappingMongoConverter converter) {
//    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
//  }
}
