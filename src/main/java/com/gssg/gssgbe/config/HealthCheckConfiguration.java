package com.gssg.gssgbe.config;

import java.time.LocalDateTime;

import org.slf4j.MDC;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthCheckConfiguration {

	@Bean
	public void healthCheck() {
		MDC.put("DEPLOY_TIME", LocalDateTime.now().toString());
	}
}
