package com.bme.onlab.userservice.customDeserializer;

import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.nio.charset.StandardCharsets;

public class KafkaRequestObjectDeserializer implements Deserializer<KafkaRequestObject> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public KafkaRequestObject deserialize(String topic, byte[] data) {
        System.out.println("faszom az egeszbe");
        try {
            if(data == null){
                System.out.println("Data not received.");
                return null;
            }
            System.out.println("Deserializer...");
            return objectMapper.readValue(new String(data, StandardCharsets.UTF_8), KafkaRequestObject.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
