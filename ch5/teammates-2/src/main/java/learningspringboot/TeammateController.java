package learningspringboot;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.Arrays;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TeammateController {

	private final TeammateRepository teammateRepository;

	@Autowired
	public TeammateController(TeammateRepository teammateRepository) {
		this.teammateRepository = teammateRepository;
	}

	@RequestMapping(value = "/teammates", method = RequestMethod.GET)
	public ModelAndView getTeammates() {
		// Specify the view name
		return new ModelAndView("teammates")
			// Look up ALL teammates and wrap each with related links
			.addObject("teammates",
				StreamSupport.stream(teammateRepository.findAll().spliterator(),
						false)
					.map(TeammateAndLink::new)
					.toArray())
			// new Teammate command object
			.addObject("teammate", new Teammate())
			.addObject("postLink",
				linkTo(methodOn(TeammateController.class).newTeammate(null))
					.withRel("Create"))
			.addObject("links", Arrays.asList(
				linkTo(methodOn(TeammateController.class).getTeammates())
					.withRel("All Teammates")
			));
	}

	@RequestMapping(value = "/teammates", method = RequestMethod.POST)
	public ModelAndView newTeammate(@ModelAttribute Teammate teammate) {
		// Save the newly created teammate
		teammateRepository.save(teammate);
		// Return the All Teammates page
		return getTeammates();
	}

	@RequestMapping(value = "/teammate/{id}", method = RequestMethod.GET)
	public ModelAndView getTeammate(@PathVariable Long id) {
		// Look up the related teammate
		final Teammate teammate = teammateRepository.findOne(id);
		return new ModelAndView("teammate")
			.addObject("teammate", teammate)
			.addObject("links", Arrays.asList(
				linkTo(methodOn(TeammateController.class).getTeammates())
						.withRel("All Teammates"),
				linkTo(methodOn(TeammateController.class).editTeammate(id))
						.withRel("Edit")
			));
	}

	@RequestMapping(value = "/teammate/{id}", method = RequestMethod.PUT)
	public ModelAndView updateTeammate(@PathVariable Long id, @ModelAttribute Teammate teammate) {
		// Connect the new teammate info with the PUT {id}
		teammate.setId(id);
		teammateRepository.save(teammate);
		// Return the teammate view
		return getTeammate(teammate.getId());
	}

	@RequestMapping(value = "/teammate/{id}/edit", method = RequestMethod.GET)
	public ModelAndView editTeammate(@PathVariable Long id) {
		final Teammate teammate = teammateRepository.findOne(id);
		return new ModelAndView("edit")
			.addObject("teammate", teammate)
			.addObject("putLink",
				linkTo(methodOn(TeammateController.class).updateTeammate(id, teammate))
					.withRel("Update"))
			.addObject("links", Arrays.asList(
				linkTo(methodOn(TeammateController.class).getTeammate(id))
					.withRel("Cancel")
			));
	}

}
