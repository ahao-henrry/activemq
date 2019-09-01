package com.ahao.mq.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @author ahao
 * @since 2019/8/29 12:03
 **/
public class SimpleConsumer {
    private static final String URL = "tcp://192.168.0.104:61616";
    private static final String QUEUE_NAME = "queue1";
    private static final String TOPIC_NAME = "topic1";

    public static void main(String[] args) throws JMSException, IOException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        queueReceive(session);
        // topicReceive(session);

        session.close();
        connection.close();
    }

    private static void queueReceive(Session session) throws JMSException, IOException {
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        // normalReceive(consumer);

        listener(consumer);
    }

    private static void topicReceive(Session session) throws JMSException, IOException {
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageConsumer consumer = session.createConsumer(topic);

        // normalReceive(consumer);

        listener(consumer);
    }

    private static void normalReceive(MessageConsumer consumer) throws JMSException {
        while (true) {
            Message message = consumer.receive();
            System.out.println(((TextMessage) message).getText());
            if (null == message) {
                break;
            }
        }

        consumer.close();
    }

    private static void listener(MessageConsumer consumer) throws JMSException, IOException {
        consumer.setMessageListener(message -> {
            if (null == message) {
                return;
            }
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println("--**" + textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        System.in.read();
        consumer.close();
    }
}
