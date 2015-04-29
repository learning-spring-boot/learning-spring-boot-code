package learningspringboot;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	DatabaseLoader databaseLoader;

	@Autowired
	TeammateRepository teammateRepository;

	@PostConstruct
	void seeTheRoster() {
		for (Teammate teammate : teammateRepository.findAll()) {
			log.info(teammate.toString());
		}
	}
}
