package com.ahao.mq.producer;

import org.apache.activemq.ScheduledMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.TextMessage;
import java.util.Date;

@Service
public class BootProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootProducer.class);

    private static final String DATETIME_FORMAT = "%tF %<tT";

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void send() {
        jmsMessagingTemplate.convertAndSend(queue, "hello, you!" + String.format(DATETIME_FORMAT, new Date()));
    }

    @Scheduled(fixedDelay = 3000)
    public void scheduledSend() {
        LOGGER.info("--**scheduledSend...");
        jmsMessagingTemplate.convertAndSend(queue, "hello, you!" + String.format(DATETIME_FORMAT, new Date()));
    }

}
