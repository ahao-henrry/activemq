package com.ahao.activemq;

import com.ahao.mq.Application;
import com.ahao.mq.producer.BootProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ActiveMQTest {
    @Autowired
    private BootProducer bootProducer;

    @Test
    public void producerTest() {
        bootProducer.send();
    }
}
