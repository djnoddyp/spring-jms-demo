package pnodder.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import pnodder.consumers.ExampleConsumer;
import pnodder.delegates.MessageDelegate;
import pnodder.listeners.ExampleListener;
import pnodder.producers.ExampleProducer;

@Configuration
public class AppConfig {

    @Bean
    public ActiveMQConnectionFactory activeMQConnectionFactory() {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL("tcp://localhost:61616");
        return activeMQConnectionFactory;
    }

    @Bean
    public ActiveMQQueue activeMQQueue() {
        ActiveMQQueue activeMQQueue = new ActiveMQQueue("messageQueue1");
        return activeMQQueue;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(activeMQConnectionFactory());
        jmsTemplate.setReceiveTimeout(10000);
        return jmsTemplate;
    }

    @Bean
    ExampleProducer jmsProducer() {
        ExampleProducer exampleProducer = new ExampleProducer();
        exampleProducer.setDestination(activeMQQueue());
        exampleProducer.setJmsTemplate(jmsTemplate());

        return exampleProducer;
    }

    @Bean
    ExampleConsumer jmsConsumer() {
        ExampleConsumer exampleConsumer = new ExampleConsumer();
        exampleConsumer.setDestination(activeMQQueue());
        exampleConsumer.setJmsTemplate(jmsTemplate());
        return exampleConsumer;
    }

    @Bean
    ExampleListener exampleListener() {
        return new ExampleListener();
    }

    @Bean
    DefaultMessageListenerContainer jmsContainer() {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(activeMQConnectionFactory());
        container.setDestination(activeMQQueue());
        //container.setMessageListener(exampleListener());
        container.setMessageListener(messageListenerAdapter());
        return container;
    }

    @Bean
    MessageListenerAdapter messageListenerAdapter() {
        MessageListenerAdapter adapter = new MessageListenerAdapter(new MessageDelegate());
        return adapter;
    }

}
