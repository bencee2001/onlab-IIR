package com.bme.onlab.user_service_api.model;

import lombok.Getter;

@Getter
public class UserCreateObject {
    private String name;
    private String username;
    private String password;
    private Integer schoolID;
}
