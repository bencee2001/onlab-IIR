package com.bme.onlab.user_service_api.model;

import lombok.Getter;

@Getter
public class UserUpdateObject {
    private Integer id;
    private String name;
    private Role role;
    private Integer classId;
}
