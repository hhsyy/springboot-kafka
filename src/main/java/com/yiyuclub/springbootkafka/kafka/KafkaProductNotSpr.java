package com.yiyuclub.springbootkafka.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaProductNotSpr {

    public KafkaProducer<String, String> getKafka(){
        Properties p = new Properties();
        //配置地址
        p.setProperty("bootstrap.servers","localhost:9092");
        //判断多少个分区接收到生产者的消息，生产者才认为成功
        //0：生产者不知道自己的消息是否被分区接收
        //1：只要有任何一个接收到，就认为成功
        //all：全部接收到才认为成功
//        p.setProperty("acks","all");
        //错误时重发次数，默认100ms
//        p.setProperty("retries","0");
        //批次大小，当有多个消息需要被发送到同一个分区时，生产者会把它们放在同一个批次里，满了发送出去
//        p.setProperty("batch.size","16384");
        //key value序列化
        p.setProperty("key.serializer", StringSerializer.class.getName());
        p.setProperty("value.serializer",StringSerializer.class.getName());

        //返回KafkaProducer
        KafkaProducer<String, String> kp = new KafkaProducer<String, String>(p);

        return kp;
    }

    public static void main(String[] args){
        //实例化类
        KafkaProductNotSpr k = new KafkaProductNotSpr();
        //得到 KafkaProducer对象
        KafkaProducer<String, String> kp = k.getKafka();
        //为KafkaProducer 指定topic和消息内容
        ProducerRecord<String, String> pr = new ProducerRecord<>("NOTSPR_TOPIC2","username","yiyu");

        //异步发送消息，同步为get
        kp.send(pr, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("进入消息反馈内");
                if(e != null){
                    System.out.println("异常："+e);
                }else{
                    System.out.println("反馈内容:"+recordMetadata.offset());
                }
            }
        });
        //关闭，不关闭消息无法发送
        kp.close();
    }

}
