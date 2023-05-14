package com.bme.onlab.user_service_api.model;

import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {
    private Integer id;
    private String name;
    private String username;
    private Role role;
    private Integer schoolId;
    private Integer classId;
    private List<String> requestGroupIDs;

    
}
