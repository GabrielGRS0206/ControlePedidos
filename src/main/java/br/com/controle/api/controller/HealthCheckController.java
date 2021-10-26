package br.com.controle.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/health")
public class HealthCheckController implements HealthIndicator {

	private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckController.class);

	@GetMapping
	@Override
	public Health health() {
		try {
			return Health.up().build();
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e.getCause());
		}
		return Health.down().build();
	}
}
