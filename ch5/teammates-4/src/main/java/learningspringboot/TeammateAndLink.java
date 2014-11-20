package learningspringboot;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import org.springframework.hateoas.Link;

public class TeammateAndLink {

	private final Teammate teammate;
	private final Link link;

	public TeammateAndLink(Teammate teammate) {
		this.teammate = teammate;
		this.link = linkTo(methodOn(TeammateController.class)
				.getTeammate(teammate.getId()))
				.withRel(teammate.getFirstName() + " " + teammate.getLastName());
	}

	public Teammate getTeammate() {
		return teammate;
	}

	public Link getLink() {
		return link;
	}
}
