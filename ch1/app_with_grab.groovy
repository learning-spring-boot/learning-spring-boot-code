//@Grab("spring-boot-starter-web")
//@Grab("groovy-templates")

@RestController
class App {
    @RequestMapping("/")
    def home() {
        "Hello, world!"
    }
}
