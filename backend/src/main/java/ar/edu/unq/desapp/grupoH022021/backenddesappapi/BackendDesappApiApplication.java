package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Import;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.SwaggerConfig;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(SwaggerConfig.class)
public class BackendDesappApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendDesappApiApplication.class, args);
	}

}
