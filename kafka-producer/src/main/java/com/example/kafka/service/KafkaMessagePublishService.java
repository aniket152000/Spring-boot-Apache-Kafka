package com.example.kafka.service;

import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import com.example.kafka.model.Message;

@Service
public class KafkaMessagePublishService {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendEventsMessageToTopic(Message message) {
        try {
            CompletableFuture<SendResult<String, Object>> future = template.send("test-msg", message);
            future.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent message=[" + message.toString() +
                            "] with offset=[" + result.getRecordMetadata().offset() + "]");
                } else {
                    System.out.println("Unable to send message=[" +
                            message.toString() + "] due to : " + ex.getMessage());
                }
            });

        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }
    }

}
