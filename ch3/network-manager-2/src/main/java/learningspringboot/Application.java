package learningspringboot;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class Application {

	public static final String MAILBOX = "events";

	// tag::jmsTemplate[]
	@Bean
	JmsTemplate jmsTemplate(ConnectionFactory factory) {
		JmsTemplate jmsTemplate = new JmsTemplate(factory);
		jmsTemplate.setPubSubDomain(true);
		return jmsTemplate;
	}
	// end::jmsTemplate[]

	@Bean
	NetworkEventSimulator simulator(JmsTemplate jmsTemplate) {
		return new NetworkEventSimulator(jmsTemplate, MAILBOX);
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
