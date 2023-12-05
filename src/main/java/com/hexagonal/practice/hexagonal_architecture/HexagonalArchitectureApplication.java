package com.hexagonal.practice.hexagonal_architecture;

import com.hexagonal.practice.hexagonal_architecture.application.controller.CliOrderController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = { "classpath:ddd-layers.properties" })
public class HexagonalArchitectureApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication application = new SpringApplication(HexagonalArchitectureApplication.class);
		// uncomment to run just the console application
		// application.setWebApplicationType(WebApplicationType.NONE);
		application.run(args);
	}

	@Autowired
	public CliOrderController orderController;

	@Autowired
	public ConfigurableApplicationContext context;

	@Override
	public void run(String... args) throws Exception {
		orderController.createCompleteOrder();
		orderController.createIncompleteOrder();
		// uncomment to stop the context when execution is done
		// context.close();
	}
}
