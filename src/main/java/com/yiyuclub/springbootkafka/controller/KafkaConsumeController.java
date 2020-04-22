package com.yiyuclub.springbootkafka.controller;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaListeners;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KafkaConsumeController {
    //多个用topic用逗号隔开
    @KafkaListener(topics = "NOTSPR_TOPIC2",groupId = "1")
    public void onMessage1(String message){
        System.out.println("接收中1");
        System.out.println(message);
    }

    //多个用topic用逗号隔开
    @KafkaListener(topics = "NOTSPR_TOPIC2",groupId = "0")
    public void onMessage2(String message){
        System.out.println("接收中2");
        System.out.println(message);
    }

    //多个用topic用逗号隔开
    @KafkaListener(topics = "NOTSPR_TOPIC2",groupId = "1")
    public void onMessage3(String message){
        System.out.println("接收中3");
        System.out.println(message);
    }
}
