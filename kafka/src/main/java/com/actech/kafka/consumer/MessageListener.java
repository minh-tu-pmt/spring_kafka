package com.actech.kafka.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import com.actech.kafka.config.KafkaConstants;
import com.actech.kafka.dto.Message;

@Component
public class MessageListener {
    
    private final SimpMessagingTemplate messagingTemplate;

    public MessageListener(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message){
        System.out.println("Message sending via kafka listener... :"+message.toString());
        messagingTemplate.convertAndSend("/topic/group", message);
    }
    
}
