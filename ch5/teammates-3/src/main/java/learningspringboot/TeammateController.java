package learningspringboot;


// tag::imports[]
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
// end::imports[]
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


// tag::class[]
@Controller
public class TeammateController {
// end::class[]

	private final TeammateRepository teammateRepository;

	@Autowired
	public TeammateController(TeammateRepository teammateRepository) {
		this.teammateRepository = teammateRepository;
	}

	// tag::getTeammates[]
	@RequestMapping(value = "/teammates", method = RequestMethod.GET)
	public ModelAndView getTeammates() {
	// end::getTeammates[]
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

	// tag::newTeammate[]
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/teammates", method = RequestMethod.POST)
	public ModelAndView newTeammate(@ModelAttribute Teammate teammate) {
	// end::newTeammate[]
		// Save the newly created teammate
		teammateRepository.save(teammate);
		// Return the All Teammates page
		return getTeammates();
	}

	// tag::getTeammate[]
	@Secured("ROLE_USER")
	@RequestMapping(value = "/teammate/{id}", method = RequestMethod.GET)
	public ModelAndView getTeammate(@PathVariable Long id) {
		ModelAndView modelAndView = new ModelAndView("teammate");
		// Look up the related teammate
		final Teammate teammate = teammateRepository.findOne(id);
		modelAndView.addObject("teammate", teammate);

		List<Link> links = new ArrayList<>();

		links.add(linkTo(methodOn(TeammateController.class).getTeammates())
				.withRel("All Teammates"));

		if (SecurityContextHolder.getContext().getAuthentication()
			.getAuthorities().stream().anyMatch(
				p -> p.getAuthority().equals("ROLE_ADMIN"))) {
				links.add(linkTo(methodOn(TeammateController.class).editTeammate(id))
					.withRel("Edit"));
		}

		modelAndView.addObject("links", links);
		return modelAndView;
	}
	// end::getTeammate[]

	// tag::updateTeammate[]
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/teammate/{id}", method = RequestMethod.PUT)
	public ModelAndView updateTeammate(@PathVariable Long id,
									   @ModelAttribute Teammate teammate) {
	// end::updateTeammate[]
		// Connect the new teammate info with the PUT {id}
		teammate.setId(id);
		teammateRepository.save(teammate);
		// Return the teammate view
		return getTeammate(teammate.getId());
	}

	// tag::editTeammate[]
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/teammate/{id}/edit", method = RequestMethod.GET)
	public ModelAndView editTeammate(@PathVariable Long id) {
	// end::editTeammate[]
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
// tag::tail[]
	}
}
// end::tail[]