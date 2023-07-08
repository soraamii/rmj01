package org.zerock.rmj01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Rmj01Application {

	public static void main(String[] args) {
		SpringApplication.run(Rmj01Application.class, args);
	}

}
