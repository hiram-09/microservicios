package com.formacionbdi.app.items;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigApp {
	
	@Bean("clientRest")
	public RestTemplate getTemplate() {
		return new RestTemplate();
	}
}
