package learningspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static final String MAILBOX = "events";

	@Bean
	NetworkEventSimulator simulator(JmsTemplate jmsTemplate, CounterService counterService) {
		return new NetworkEventSimulator(jmsTemplate, MAILBOX, counterService);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
