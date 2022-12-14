package kr.co.souso.souso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class SousoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SousoApplication.class, args);
	}
}
