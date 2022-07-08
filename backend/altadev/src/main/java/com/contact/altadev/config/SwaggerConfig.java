package com.contact.altadev.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class SwaggerConfig {

	public static final String SERVER_LOCAL = "http://localhost:8080/";
	private static final String API_VERSION = "v1.0";
	private static final String API_TITLE = "SOS Combustíveis";
	private static final String API_DESCRIPTION = "API de Integração para Frontend";

	@Bean
	public OpenAPI api() {
		return new OpenAPI()
				.info(apiInfo())
				.servers(Arrays.asList(
						new Server().url(SERVER_LOCAL).description("Dev - Local")));
	}

	private Info apiInfo() {
		return new Info()
				.title(API_TITLE)
				.description(API_DESCRIPTION)
				.version(API_VERSION)
				.extensions(extensions());
	}

	private Map<String, Object> extensions() {
		Map<String, Object> map = new HashMap<>();
		map.put("Bearer ", "asdfasdfadf");
		return map;
	}
}