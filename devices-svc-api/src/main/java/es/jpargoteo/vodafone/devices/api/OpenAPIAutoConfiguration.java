package es.jpargoteo.vodafone.devices.api;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;

public class OpenAPIAutoConfiguration {

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI baseOpenAPI() {
        return new OpenAPI().components(new Components())
                .info(new Info()
                        .title(buildProperties.getArtifact() + "-api")
                        .description("This is the version "
                                + buildProperties.getVersion()
                                + " of the "
                                + buildProperties.getArtifact()
                                + " API REST")
                        .termsOfService("")
                        .version(buildProperties.getVersion()));
    }
}
