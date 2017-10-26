package pl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import pl.fileupload.StorageProperties;
import pl.fileupload.StorageService;

@SpringBootApplication
@EnableConfigurationProperties(StorageProperties.class)
public class VocabularyanalyzeappApplication {

	public static void main(String[] args) {
		SpringApplication.run(VocabularyanalyzeappApplication.class, args);

	}

//	@Bean
	CommandLineRunner init(StorageService storageService) {
		return (args) -> {
			storageService.deleteAll();
			storageService.init();
		};
	}
}
