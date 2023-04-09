package com.bme.onlab.errors;

import java.io.Serializable;

public class NoSuchUserException extends Exception implements Serializable {

    public NoSuchUserException(){
    }

    public NoSuchUserException(String msg){
        super(msg);
    }
}
