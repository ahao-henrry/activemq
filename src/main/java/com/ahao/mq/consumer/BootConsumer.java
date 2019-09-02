package com.ahao.mq.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.TextMessage;

@Component
public class BootConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(BootConsumer.class);

    @JmsListener(destination = "${destination}")
    public void listener(TextMessage textMessage) throws JMSException {
        LOGGER.info("--**received text message : {}", textMessage.getText());
    }
}
