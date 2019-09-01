package com.ahao.mq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author ahao
 * @since 2019/8/29 11:19
 **/
public class SimpleProducer {
    private static final String URL = "tcp://192.168.0.104:61616";
    private static final String QUEUE_NAME = "queue1";
    private static final String TOPIC_NAME = "topic1";


    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        queueProducer(session);
        // topicProducer(session);

        session.close();
        connection.close();
    }

    private static void queueProducer(Session session) throws JMSException {
        Queue queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);

        producer.send(session.createTextMessage("Hello, you!"));
        producer.setDeliveryMode(DeliveryMode.PERSISTENT); // 默认持久化

        producer.close();
    }

    private static void topicProducer(Session session) throws JMSException {
        Topic topic = session.createTopic(TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);

        producer.send(session.createTextMessage("Hello, he!"));

        producer.close();
    }
}
