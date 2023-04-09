package com.bme.onlab.userservice;

import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
public class KafkaListeners {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /*@KafkaListener(topics = "newRequestGroup", groupId = "foo")
    void listener(String data) throws JsonProcessingException {
        KafkaRequestObject obj = objectMapper.readValue(data, KafkaRequestObject.class);
        System.out.println("Received data: " + data + "   " + obj.getReceiverID());
    }*/
}
