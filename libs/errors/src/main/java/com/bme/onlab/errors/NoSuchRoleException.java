package com.bme.onlab.errors;

import java.io.Serializable;

public class NoSuchRoleException extends Exception implements Serializable {

    public NoSuchRoleException(){}

    public NoSuchRoleException(String msg){
        super(msg);
    }
}
