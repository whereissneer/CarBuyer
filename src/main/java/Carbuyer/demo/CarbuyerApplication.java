package Carbuyer.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import Carbuyer.demo.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
public class CarbuyerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CarbuyerApplication.class, args);
	}

}
