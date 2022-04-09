package com.simudest.simudest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = "com.simudest.simudest.repository")
@SpringBootApplication
@EntityScan("com.simudest.simudest.*")
@ComponentScan(basePackages = { "com.simudest.simudest.*" })
public class SimudestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimudestApplication.class, args);
	}
}
