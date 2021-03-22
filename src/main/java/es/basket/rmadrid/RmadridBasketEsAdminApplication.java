package es.basket.rmadrid;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class RmadridBasketEsAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(RmadridBasketEsAdminApplication.class, args);
	}

}
