package com.example.kafka.controller;

import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.kafka.constants.TextMessages;
import com.example.kafka.model.Message;
import com.example.kafka.service.KafkaMessagePublishService;

@RestController
@RequestMapping("/producer")
public class KafkaController {

    @Autowired
    private KafkaMessagePublishService publisher;

    @GetMapping("/publish/message")
    public ResponseEntity<?> sendMessageEvents() {
        Random random = new Random();

        try {
            for (int i = 0; i <= 10000; i++) {
                // Create a new Message instance for each iteration
                Message message = new Message();

                String msg = TextMessages.MESSAGES[random.nextInt(TextMessages.MESSAGES.length)];
                message.setId(i);
                message.setTextMessage(msg);

                publisher.sendEventsMessageToTopic(message);
            }
            return ResponseEntity.ok("message published successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }

}
