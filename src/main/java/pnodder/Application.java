package pnodder;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pnodder.config.JmsAppConfig;
import pnodder.consumers.JmsConsumer;
import pnodder.producers.JmsProducer;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws URISyntaxException, Exception {
        BrokerService brokerService = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        brokerService.start();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(JmsAppConfig.class);
        try {
            JmsProducer jmsProducer = (JmsProducer) ctx.getBean("jmsProducer");
            jmsProducer.sendMessage("whats happening");
            JmsConsumer jmsConsumer = (JmsConsumer) ctx.getBean("jmsConsumer");
            System.out.println("Received: " + jmsConsumer.receiveMessage());
        } finally {
            brokerService.stop();

        }
    }

}
