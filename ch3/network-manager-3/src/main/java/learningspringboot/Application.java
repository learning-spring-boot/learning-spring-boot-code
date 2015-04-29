package learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static final String MAILBOX = "events";

	@Bean
	NetworkEventSimulator simulator(JmsTemplate jmsTemplate) {
		return new NetworkEventSimulator(jmsTemplate, MAILBOX);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
