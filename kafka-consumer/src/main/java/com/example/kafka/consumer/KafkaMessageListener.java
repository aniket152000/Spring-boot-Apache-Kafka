package com.example.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.kafka.model.Message;
import com.example.kafka.repository.MessageRepository;

@Service
public class KafkaMessageListener {

    @Autowired
    private MessageRepository messageRepository;

    Logger log = LoggerFactory.getLogger(KafkaMessageListener.class);

    @KafkaListener(topics = "test-msg", groupId = "Id")
    public void consumeEvents(Message message) {
        messageRepository.save(message);
        log.info("consumer 1 consume the events {} ", message.toString());
    }

}
