package com.yiyuclub.springbootkafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KafkaProductController {
    @Autowired
    public KafkaTemplate<Object, Object> kafkaTemplate;

    @GetMapping("asyncsend")
    public String asyncSend(@RequestParam String message){
        try {
            ListenableFuture<SendResult<Object, Object>> send = kafkaTemplate.send("NOTSPR_TOPIC2", message );
            send.addCallback(new ListenableFutureCallback(){
                @Override
                public void onSuccess(Object o) {
                    System.out.println("进入成功："+o);
                }
                @Override
                public void onFailure(Throwable throwable) {
                    System.out.println("进入失败："+throwable);
                }
            });
        }catch (Exception e){
            return "系统异常";
        }
        return "已处理";
    }

    @GetMapping("syncsend")
    public String syncSend(@RequestParam String message){
        try {
            SendResult<Object, Object> result = kafkaTemplate.send("NOTSPR_TOPIC2", message).get();
            System.out.println("记录："+result.getProducerRecord());
        }catch (Exception e){
            return "系统异常";
        }
        return "已处理";
    }
}
