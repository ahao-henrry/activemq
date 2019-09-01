package com.ahao.mq.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @author ahao
 * @since 2019/8/29 21:24
 **/
public class DurableTopicProducer {
    private static final String URL = "tcp://192.168.0.104:61616";
    private static final String DURABLE_TOPIC_NAME = "durable_topic1";

    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic = session.createTopic(DURABLE_TOPIC_NAME);
        MessageProducer producer = session.createProducer(topic);
        producer.setDeliveryMode(DeliveryMode.PERSISTENT);
        connection.start();

        producer.send(session.createTextMessage("Hello, durable you!"));

        producer.close();
        session.close();
        connection.close();
    }
}
