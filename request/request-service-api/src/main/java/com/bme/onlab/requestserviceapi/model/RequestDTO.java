package com.bme.onlab.requestserviceapi.model;

import lombok.Getter;

import java.text.SimpleDateFormat;

@Getter
public class RequestDTO {

    private static final SimpleDateFormat dateFormat
            = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    private Integer senderID;
    private Integer recieverID;

    private String message;


}
