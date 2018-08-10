package br.com.ciclic.brewery.beer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
public class BeerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeerApplication.class, args);
	}
}

