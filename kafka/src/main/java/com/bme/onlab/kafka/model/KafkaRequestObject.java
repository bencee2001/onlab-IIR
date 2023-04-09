package com.bme.onlab.kafka.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
public class KafkaRequestObject {
    private Integer senderID;
    private Integer receiverID;
    private String requestGroupID;
}
