package es.jpargoteo.vodafone.devices.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"es.jpargoteo.vodafone.devices.model.business", "es.jpargoteo.vodafone.devices.ws.service"})
@EntityScan("es.jpargoteo.vodafone.devices.model.domain")
@EnableJpaRepositories("es.jpargoteo.vodafone.devices.model.repository")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}

