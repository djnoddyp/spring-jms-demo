package pnodder;

import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pnodder.config.AppConfig;
import pnodder.consumers.ExampleConsumer;
import pnodder.producers.ExampleProducer;

import java.net.URI;
import java.net.URISyntaxException;

public class Application {

    public static void main(String[] args) throws URISyntaxException, Exception {
        // Use an embedded activemq broker
        BrokerService brokerService = BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)"));
        brokerService.start();
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        try {
            ExampleProducer exampleProducer = (ExampleProducer) ctx.getBean("jmsProducer");
            exampleProducer.sendMessage("whats happening");
            ExampleConsumer consumer = (ExampleConsumer) ctx.getBean("jmsConsumer");
            System.out.println("Received: " + consumer.receiveMessage());
        } finally {
            brokerService.stop();
        }
    }

}
