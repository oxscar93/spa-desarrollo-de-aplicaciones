package ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer{

	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(usersApiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices"))
				.paths(regex("/api.*"))
				.build()
				;
	}

	private ApiInfo usersApiInfo() {
        return new ApiInfoBuilder()
                .title("Service User")
                .version("1.0")
                .license("Apache License Version 2.0")
                .build();
    }

	@Override
	 public void addResourceHandlers(ResourceHandlerRegistry registry) {
	 registry.addResourceHandler("swagger-ui.html")
	        .addResourceLocations("classpath:/META-INF/resources/");
	 registry.addResourceHandler("/webjars/**")
	        .addResourceLocations("classpath:/META-INF/resources/webjars/");
	 }
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
	    registry.addRedirectViewController("/api/v2/api-docs", "/v2/api-docs");
	    registry.addRedirectViewController("/api/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
	    registry.addRedirectViewController("/api/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
	    registry.addRedirectViewController("/api/swagger-resources", "/swagger-resources");
	}
}
