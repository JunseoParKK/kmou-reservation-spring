package capston.kmouReserveApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class KmouReserveAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(KmouReserveAppApplication.class, args);
	}
}
