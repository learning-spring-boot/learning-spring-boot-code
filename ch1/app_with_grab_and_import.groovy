//@Grab("spring-boot-starter-web")
//@Grab("groovy-templates")

//import org.springframework.web.bind.annotation.*
//import org.springframework.web.servlet.config.annotation.*
//import org.springframework.web.servlet.*
//import org.springframework.web.servlet.handler.*
//import org.springframework.http.*
//import org.springframework.ui.*

//static import org.springframework.boot.cli.template.GroovyTemplate.template

@RestController
class App {
    @RequestMapping("/")
    def home() {
        "Hello, world!"
    }
}
