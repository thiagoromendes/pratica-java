package com.evoluum.java.evoluumjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"com.evoluum.java.evoluumjava.entidades"})
@EnableJpaRepositories(basePackages = {"com.evoluum.java.evoluumjava.repositorio"})
@ComponentScan(basePackages = {"com.evoluum.java.evoluumjava.servico","com.evoluum.java.evoluumjava.controlador"})
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
