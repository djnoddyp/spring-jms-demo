package pnodder.consumers;

import org.springframework.jms.core.JmsTemplate;
import pnodder.model.Email;

import javax.jms.Destination;
import javax.jms.JMSException;

public class ExampleConsumer {

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

    public String receiveMessage() throws JMSException {
        Email email = (Email) jmsTemplate.receiveAndConvert(destination);
        return email.getBody();
    }


}
