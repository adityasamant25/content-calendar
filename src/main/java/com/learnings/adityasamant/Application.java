package com.learnings.adityasamant;

import com.learnings.adityasamant.config.ContentCalendarProperties;
import com.learnings.adityasamant.model.Content;
import com.learnings.adityasamant.model.Status;
import com.learnings.adityasamant.model.Type;
import com.learnings.adityasamant.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@EnableConfigurationProperties(ContentCalendarProperties.class)
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository) {
		return args -> {
			Content content = new Content(null,"Hello Chat GPT", "All about Chat GPT",
					Status.IDEA, Type.VIDEO, LocalDateTime.now(), null, "");
			repository.save(content);
		};
	}
}
