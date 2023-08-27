package by.it.medved;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class CinemaWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(CinemaWebApplication.class, args);
	}

}
