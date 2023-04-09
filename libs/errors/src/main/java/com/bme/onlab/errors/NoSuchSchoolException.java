package com.bme.onlab.errors;

import org.hibernate.jdbc.Expectations;

import java.io.Serializable;

public class NoSuchSchoolException extends Exception implements Serializable {

    public NoSuchSchoolException(){
    }

    public NoSuchSchoolException(String msg){
        super(msg);
    }
}
