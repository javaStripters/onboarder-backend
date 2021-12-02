package ru.javastripters.onboarder;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableAdminServer
public class OnboarderApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnboarderApplication.class, args);
	}

	@Bean
	OpenApiCustomiser setServers() {
		return openApi -> openApi.setServers(List.of(
				new Server().url("http://129.159.248.238").description("Remote"),
				new Server().url("http://localhost:8080").description("Local"),
				new Server().url("http://localhost").description("Local Docker")));
	}
}
