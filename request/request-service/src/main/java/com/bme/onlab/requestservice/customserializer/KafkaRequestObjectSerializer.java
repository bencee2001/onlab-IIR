package com.bme.onlab.requestservice.customserializer;

import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class KafkaRequestObjectSerializer implements Serializer<KafkaRequestObject> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, KafkaRequestObject kafkaRequestObject) {
        try {
        if(kafkaRequestObject == null){
            System.out.println("Null value received");
            return null;
        }
        System.out.println("Serializing...");
        return objectMapper.writeValueAsBytes(kafkaRequestObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
