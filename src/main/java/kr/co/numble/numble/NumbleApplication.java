package kr.co.numble.numble;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class NumbleApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumbleApplication.class, args);
	}

}
