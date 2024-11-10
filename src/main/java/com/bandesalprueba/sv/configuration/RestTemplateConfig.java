package com.bandesalprueba.sv.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Value("${app.conf.rest.timeout}")
	private int timeOut;

	@Bean
	RestTemplate restTemplate() {

		return new RestTemplate(getClientHttpRequestFactory());
	}

	private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
		SimpleClientHttpRequestFactory clientHttpRequestFactory = new SimpleClientHttpRequestFactory();

		clientHttpRequestFactory.setConnectTimeout(timeOut);

		clientHttpRequestFactory.setReadTimeout(timeOut);

		return clientHttpRequestFactory;
	}

}
