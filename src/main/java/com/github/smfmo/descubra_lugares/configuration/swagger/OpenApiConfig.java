package com.github.smfmo.descubra_lugares.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API de lugares e pontos turísticos para visitar (Descubra lugares)",
                version = "1.0",
                contact = @Contact(
                        name = "Samuel Monteiro Ferreira",
                        email = "smf.ferreira1901@gmail.com",
                        url = "https://www.linkedin.com/in/samuel-monteiroo/"
                )
        )
)
public class OpenApiConfig {
}
