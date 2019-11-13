package com.test.springBoot.kafka;





import com.kiisoo.commons.component.kafka.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Properties;



@Service
public class ProducerTest {
    @Autowired
    KafkaProducer kafkaProducer;
    public void send(){
        kafkaProducer.send("test","msg test java");
    }
}
