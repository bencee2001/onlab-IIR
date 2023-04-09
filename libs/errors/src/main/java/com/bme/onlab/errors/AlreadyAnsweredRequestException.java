package com.bme.onlab.errors;

import java.io.Serializable;

public class AlreadyAnsweredRequestException extends Exception implements Serializable {

    public AlreadyAnsweredRequestException(){}

    public AlreadyAnsweredRequestException(String msg){
        super(msg);
    }
}
