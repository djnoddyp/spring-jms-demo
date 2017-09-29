package pnodder.listeners;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Consume message in the MDP (message-driven POJO) style
 */
public class ExampleListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("got it");
    }
}
