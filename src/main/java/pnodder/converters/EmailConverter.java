package pnodder.converters;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import pnodder.model.Email;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

public class EmailConverter implements MessageConverter {

    @Override
    public Message toMessage(Object o, Session session) throws JMSException, MessageConversionException {
        Email email = (Email) o;
        Message message = session.createTextMessage();
        message.setStringProperty("to", email.getTo());
        message.setStringProperty("from", email.getFrom());
        message.setStringProperty("body", email.getBody());
        return message;
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {
        Email email = new Email(message.getStringProperty("to"),
                message.getStringProperty("from"),
                message.getStringProperty("body"));
        return email;
    }
}
