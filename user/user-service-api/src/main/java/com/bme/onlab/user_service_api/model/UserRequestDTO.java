package com.bme.onlab.user_service_api.model;

import lombok.Getter;

import java.util.List;

@Getter
public class UserRequestDTO {
    private Integer id;
    private List<String> requestGroupIDs;
}
