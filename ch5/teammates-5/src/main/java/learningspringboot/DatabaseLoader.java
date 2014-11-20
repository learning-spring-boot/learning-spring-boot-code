package learningspringboot;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatabaseLoader {

	private final TeammateRepository teammateRepository;

	@Autowired
	public DatabaseLoader(TeammateRepository teammateRepository) {
		this.teammateRepository = teammateRepository;
	}

	// tag::init[]
	@PostConstruct
	private void initDatabase() {
		teammateRepository.deleteAll();

		Teammate roy = new Teammate("Roy", "Clarkson");
		roy.setPosition("1st base");
		teammateRepository.save(roy);

		Teammate phil = new Teammate("Phil", "Webb");
		phil.setPosition("pitcher");
		teammateRepository.save(phil);
	}
	// end::init[]
}
