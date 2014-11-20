package learningspringboot;

import javax.jms.ConnectionFactory;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@ComponentScan
@EnableScheduling
@EnableAutoConfiguration
public class Application {

	private static final String MAILBOX = "events";

	@Bean
	MessageListenerAdapter adapter(NetworkEventConsumer consumer) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(consumer);
		adapter.setDefaultListenerMethod("process");
		return adapter;
	}

	// tag::consumer[]
	@Bean
	SimpleMessageListenerContainer container(MessageListenerAdapter adapter,
			ConnectionFactory factory) {
		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer();
		container.setMessageListener(adapter);
		container.setConnectionFactory(factory);
		container.setPubSubDomain(true);
		container.setDestinationName(MAILBOX);
		return container;
	}
	// end::consumer[]

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
