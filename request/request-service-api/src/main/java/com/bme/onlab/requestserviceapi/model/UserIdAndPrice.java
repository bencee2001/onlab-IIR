package com.bme.onlab.requestserviceapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class UserIdAndPrice {

    Integer userId;
    BigDecimal price;
}
