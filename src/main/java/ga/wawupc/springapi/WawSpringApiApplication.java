package ga.wawupc.springapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition
@SpringBootApplication
@EnableJpaAuditing
public class WawSpringApiApplication {
  public static void main(String[] args) {
    SpringApplication.run(WawSpringApiApplication.class, args);
  }
}
