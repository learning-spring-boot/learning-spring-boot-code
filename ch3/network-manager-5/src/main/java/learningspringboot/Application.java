package learningspringboot;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jms.JmsProperties;
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

	@Autowired
	private JmsProperties properties;

	@Bean
	MessageListenerAdapter adapter(NetworkEventConsumer consumer) {
		MessageListenerAdapter adapter = new MessageListenerAdapter(consumer);
		adapter.setDefaultListenerMethod("process");
		return adapter;
	}

	@Bean
	SimpleMessageListenerContainer container(MessageListenerAdapter consumer,
											 ConnectionFactory factory) {
		SimpleMessageListenerContainer container =
				new SimpleMessageListenerContainer();
		container.setMessageListener(consumer);
		container.setConnectionFactory(factory);
		container.setPubSubDomain(this.properties.isPubSubDomain());
		container.setDestinationName(MAILBOX);
		return container;
	}

	// tag::simulator[]
	@Bean
	NetworkEventSimulator simulator(JmsTemplate jmsTemplate, CounterService counterService) {
		return new NetworkEventSimulator(jmsTemplate, MAILBOX, counterService);
	}
	// end::simulator[]

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
