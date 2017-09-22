package pnodder.config;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import pnodder.consumers.JmsConsumer;
import pnodder.producers.JmsProducer;

@Configuration
public class JmsAppConfig {

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
    JmsProducer jmsProducer() {
        JmsProducer jmsProducer = new JmsProducer();
        jmsProducer.setDestination(activeMQQueue());
        jmsProducer.setJmsTemplate(jmsTemplate());
        return jmsProducer;
    }

    @Bean
    JmsConsumer jmsConsumer() {
        JmsConsumer jmsConsumer = new JmsConsumer();
        jmsConsumer.setDestination(activeMQQueue());
        jmsConsumer.setJmsTemplate(jmsTemplate());
        return jmsConsumer;
    }

}
