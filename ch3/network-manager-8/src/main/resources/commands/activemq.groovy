package learningspringboot.commands

import org.crsh.cli.Command
import org.crsh.cli.Man
import org.crsh.cli.Usage
import org.crsh.command.InvocationContext
import org.springframework.beans.factory.BeanFactory
import org.springframework.boot.actuate.metrics.repository.MetricRepository

import javax.jms.ConnectionFactory

@Usage("Various commands to interact with ActiveMQ JMS broker")
class activemq {

    @Usage("Check ActiveMQ status")
    @Man("Creates a connection to the broker. If successful, report 'UP'. If not, report 'DOWN'")
    @Command
    def ping(InvocationContext context) {
        BeanFactory beanFactory = context.attributes['spring.beanfactory']
        try {
            beanFactory.getBean(ConnectionFactory).createConnection()
            "Broker is UP!"
        } catch (JMSException) {
            "Broker is DOWN!"
        }
    }

    @Usage("Print out ActiveMQ metrics")
    @Man("Iterate over all metrics, and print out any that involves 'messages'")
    @Command
    void metrics(InvocationContext context) {
        BeanFactory beanFactory = context.attributes['spring.beanfactory']
        def metricRepository = beanFactory.getBean(MetricRepository)
        metricRepository.findAll().each { metric ->
            if (metric.name.startsWith("counter.messages")) {
                out.println "${metric.name}: ${metric.value}"
            }
        }
    }

}