package learningspringboot;

import javax.annotation.PostConstruct;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Service
@Profile("!production")
public class DatabaseLoader {

	private final TeammateRepository teammateRepository;
	private final TeamRepository teamRepository;

	@Autowired
	public DatabaseLoader(TeammateRepository teammateRepository, TeamRepository teamRepository) {
		this.teammateRepository = teammateRepository;
		this.teamRepository = teamRepository;
	}

	@PostConstruct
	private void initDatabase() {
		teamRepository.deleteAll();
		teammateRepository.deleteAll();

		Team springBootTeam = new Team("Spring Boot Badgers");
		teamRepository.save(springBootTeam);

		Teammate greg = new Teammate("Greg", "Turnquist");
		greg.setPosition("2nd base");
		greg.setTeamId(springBootTeam.getId());
		teammateRepository.save(greg);

		Teammate roy = new Teammate("Roy", "Clarkson");
		roy.setPosition("1st base");
		roy.setTeamId(springBootTeam.getId());
		teammateRepository.save(roy);

		Teammate phil = new Teammate("Phil", "Webb");
		phil.setPosition("pitcher");
		phil.setTeamId(springBootTeam.getId());
		teammateRepository.save(phil);

		springBootTeam.setMembers(Arrays.asList(new Teammate[]{greg, roy, phil}));
		teamRepository.save(springBootTeam);
	}

}
