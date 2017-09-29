package pnodder.producers;

import org.springframework.jms.core.JmsTemplate;
import pnodder.model.Email;

import javax.jms.Destination;

public class ExampleProducer {

    private JmsTemplate jmsTemplate;
    private Destination destination;

    public JmsTemplate getJmsTemplate() {
        return jmsTemplate;
    }

    public void setJmsTemplate(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public void sendMessage(final String msg) {
        Email email = new Email("Randy", "Jim", "Coupla drinks?");
        System.out.println("Sending message");
        jmsTemplate.convertAndSend(destination, email);
    }
}
