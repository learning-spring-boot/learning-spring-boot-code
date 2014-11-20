package learningspringboot;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Alarm implements Serializable {

	final private String hostname;
	final private LocalDateTime eventTime;
	final private Severity severity;

	public Alarm(String hostname, LocalDateTime eventTime, Severity severity) {
		this.hostname = hostname;
		this.eventTime = eventTime;
		this.severity = severity;
	}

	public String getHostname() {
		return hostname;
	}

	public LocalDateTime getEventTime() {
		return eventTime;
	}

	public Severity getSeverity() {
		return severity;
	}

	public String toString() {
		return "Event[" + hostname + ":" + severity + "]";
	}
}
