package com.bme.onlab.requestserviceapi.model;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Integer senderID;
    private Integer recieverID;

    private String message;
    private Date sendDate;
    @Enumerated(EnumType.STRING)
    private Status status;

    private String groupId;
}
