package com.bme.onlab.requestserviceapi.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Table(name = "request")
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

    private BigDecimal price;

}
