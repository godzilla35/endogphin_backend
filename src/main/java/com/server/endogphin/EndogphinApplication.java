package com.server.endogphin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class EndogphinApplication {

	public static void main(String[] args) {
		SpringApplication.run(EndogphinApplication.class, args);
	}

}
