package learningspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.metrics.CounterService;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NetworkEventConsumer {

	private static final Logger log =
			LoggerFactory.getLogger(NetworkEventConsumer.class);

	private final CounterService counterService;

	@Autowired
	public NetworkEventConsumer(CounterService counterService) {
		this.counterService = counterService;
	}

	@JmsListener(destination = Application.MAILBOX)
	public void process(Alarm event) {
		log.info("Processing " + event);
		counterService.increment("messages.total.consumed");
		counterService.increment("messages." + event.getHostname() + ".consumed");
	}
}
