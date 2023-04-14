package com.bme.onlab.requestserviceapi.model;

import lombok.Getter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

@Getter
public class CreateRequestObject {

    private Integer senderID;
    private Integer recieverID;

    private String message;
    private BigDecimal price;


}
