package br.com.ciclic.brewery.beer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class BeerApplication {
	public static void main(String[] args) {
		SpringApplication.run(BeerApplication.class, args);
	}
}

