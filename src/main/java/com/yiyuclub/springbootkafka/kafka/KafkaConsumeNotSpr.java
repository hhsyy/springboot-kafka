package com.yiyuclub.springbootkafka.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Properties;

public class KafkaConsumeNotSpr {
    public  KafkaConsumer<String,String> getKafka(){
        Properties p = new Properties();
        //配置地址
        p.setProperty("bootstrap.servers","localhost:9092");
        //消费群组id,非必需
        p.setProperty("group.id", "0");
        //消费者是否自动提交偏移量，默认值是 true
//        p.setProperty("enable.auto.commit","true");
        //提交时间间隔
//        p.setProperty("auto.commit.interval.ms", "1000");
        //没有发送心跳指令认定死亡时间，默认3000ms
//        p.setProperty("session.timeout.ms", "3500");
        //控制单次调用 call() 方法能够返回的记录数量
//        p.setProperty("max.poll.records", "10");
        //消费者在读取一个没有偏移量的分区或者偏移量无效的情况下的该如何处理
        //默认latest：在偏移量无效的情况下，消费者将从最新的记录开始读取数据
        //earliest：在偏移量无效的情况下，消费者将从起始位置处开始读取分区的记录
//        p.setProperty("auto.offset.reset", "earliest");
        //key value序列化
        p.setProperty("key.deserializer", StringDeserializer.class.getName());
        p.setProperty("value.deserializer", StringDeserializer.class.getName());
        //返回KafkaConsumer对象
        KafkaConsumer<String,String> k = new KafkaConsumer<String, String>(p);
        return k;
    }

    public static void main(String[] args){
        //实例化类
        KafkaConsumeNotSpr kc = new KafkaConsumeNotSpr();
        //获得KafkaConsumer对象
        KafkaConsumer<String,String> k = kc.getKafka();
        //指定接收topic
        k.subscribe(Arrays.asList("NOTSPR_TOPIC2"));
        while(true){
            //接收消息
            ConsumerRecords<String, String> records = k.poll(Duration.ofMillis(3000));
            System.out.println("记录数："+records.count());
            records.forEach((ConsumerRecord<String,String> record)->{
                System.out.println("-----------------");
                System.out.printf("offset = %d, value = %s", record.offset(), record.value());
                System.out.println();

            });
        }
    }
}
