package com.actech.kafka.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Message {
    private String sender;
    private String content;
    private String timestamp;

}