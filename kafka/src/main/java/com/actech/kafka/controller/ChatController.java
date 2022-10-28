package com.actech.kafka.controller;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.actech.kafka.config.KafkaConstants;
import com.actech.kafka.dto.Message;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = "/api/message")
public class ChatController {
    
    private final KafkaTemplate<String, Message> kafkaTemplate;

    public ChatController(KafkaTemplate<String, Message> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PostMapping(value="/send")
    public void sendMessage(@RequestBody Message message) throws ExecutionException, InterruptedException {
        
        message.setTimestamp(LocalDateTime.now().toString());
        kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message).get();
    }
    
    //    -------------- WebSocket API ----------------
    @MessageMapping("/sendMessage")
    @SendTo("/topic/group")
    public Message broadcastGroupMessage(@Payload Message message) {
        //Sending this message to all the subscribers
        return message;
    }

    @MessageMapping("/newUser")
    @SendTo("/topic/group")
    public Message addUser(@Payload Message message,
                           SimpMessageHeaderAccessor headerAccessor) {
        // Add user in web socket session
        headerAccessor.getSessionAttributes().put("username", message.getSender());
        return message;
    }

}
