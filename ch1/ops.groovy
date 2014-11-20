@Grab("spring-boot-actuator")
@Grab("spring-boot-starter-remote-shell")
@Grab("org.webjars:jquery:2.1.1")
@Grab("thymeleaf-spring4")
@Controller
class OpsReadyApp {
    @RequestMapping("/")
    def home(@RequestParam(value="name", defaultValue="World") String n) {
        new ModelAndView("modern")
            .addObject("name", n)
    }
}
