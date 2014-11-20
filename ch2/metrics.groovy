package learningspringboot

@Grab("groovy-all")
import groovy.json.*

@EnableScheduling
class MetricsCollector {

    def url = "http://localhost:8080/metrics"
    def slurper = new JsonSlurper()
    def keys = slurper.parse(new URL(url)).keySet()
        .findAll{
            it.startsWith("counter")
        }
    def header = false;
    
    @Scheduled(fixedRate = 1000L)
    void run() {
        if (!header) {
            println(keys.join(','))
            header = true
        }
        
        def metrics = slurper.parse(new URL(url))
        
        println(keys.collect{metrics[it]}.join(','))
    }

}