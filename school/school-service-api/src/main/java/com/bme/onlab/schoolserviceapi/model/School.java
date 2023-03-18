package com.bme.onlab.schoolserviceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class School {
    @Id
    private Integer id;
    private String schoolName;
    private BigDecimal budget;
}
