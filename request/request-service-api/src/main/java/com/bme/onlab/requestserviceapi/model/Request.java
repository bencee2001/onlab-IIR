package com.bme.onlab.requestserviceapi.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Request {

    @Id
    private Integer id;

    private Integer senderID;
    private Integer recieverID;

    private String message;
    private Date sendDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    private Integer groupId;
}
