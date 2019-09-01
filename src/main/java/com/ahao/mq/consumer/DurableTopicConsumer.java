package com.ahao.mq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author ahao
 * @since 2019/8/29 21:33
 **/
public class DurableTopicConsumer {
    private static final String URL = "tcp://192.168.0.104:61616";
    private static final String DURABLE_TOPIC_NAME = "durable_topic1";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.setClientID("ahao");
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(DURABLE_TOPIC_NAME);
        TopicSubscriber subscriber = session.createDurableSubscriber(topic, "ahao subscriber");
        connection.start();

        listener(subscriber);

        subscriber.close();
        session.close();
        connection.close();
    }

    private static void listener(TopicSubscriber subscriber) throws JMSException, IOException {
        subscriber.setMessageListener(message -> {
            if (message instanceof TextMessage) {
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        System.in.read();
    }
}
