package net.herit.ami;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringRookieQuartzApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringRookieQuartzApplication.class, args);
	}

}
