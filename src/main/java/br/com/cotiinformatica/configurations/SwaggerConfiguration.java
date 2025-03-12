package br.com.cotiinformatica.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfiguration {
	@Bean
	OpenAPI customOpenApi() {
		
		var openApi = new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title("Usuários API - COTI Informática")
						.description("API Spring Boot para controle de usuários e perfis.")
						.version("v1"));
		
		return openApi;
	}
}


