package com.bme.onlab.requestserviceapi.model;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private Integer id;

    private Integer senderID;
    private String senderName;
    private Integer recieverID;
    private String recieverName;

    private String message;
    private Date sendDate;
    private Status status;
    private String groupId;
    private BigDecimal price;

    public RequestDTO(Request request){
        RequestDTO.builder()
                .id(request.getId())
                .senderID(request.getSenderID())
                .recieverID(request.getRecieverID())
                .message(request.getMessage())
                .sendDate(request.getSendDate())
                .status(request.getStatus())
                .groupId(request.getGroupId())
                .price(request.getPrice())
                .build();
    }
}
