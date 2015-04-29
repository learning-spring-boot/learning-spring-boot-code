package learningspringboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class NetworkEventConsumer {

	private static final Logger log =
			LoggerFactory.getLogger(NetworkEventConsumer.class);

	@JmsListener(destination = Application.MAILBOX)
	public void process(Alarm event) {
		log.info("Processing " + event);
	}
}
