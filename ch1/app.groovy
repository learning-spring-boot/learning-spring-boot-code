@RestController
class App {
    @RequestMapping("/")
    def home() {
        "Hello, world!"
    }
}
