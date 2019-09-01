package com.ahao.mq.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;
import java.util.Date;

@Service
public class BootProducer {
    private static final String DATETIME_FORMAT = "%tF %<tT";
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private Queue queue;

    public void send() {
        jmsMessagingTemplate.convertAndSend(queue, "hello, you!" + String.format(DATETIME_FORMAT, new Date()));
    }

}
