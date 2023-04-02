package com.bme.onlab.requestservice.service;

import com.bme.onlab.requestservice.repository.RequestRepository;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.RequestDTO;
import com.bme.onlab.requestserviceapi.model.Status;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {

    final private RequestRepository requestRepository;

    public String createGroup(RequestDTO requestDTO){
        String uuid = UUID.randomUUID().toString();
        Request request =
                Request.builder()
                        .senderID(requestDTO.getSenderID())
                        .recieverID(requestDTO.getRecieverID())
                        .sendDate(new Date())
                        .groupId(uuid)
                        .status(Status.Asking)
                        .build();

        requestRepository.save(request);
        return uuid;
    }

    public void createRequest(Integer requestID, String message){
        Request request = requestRepository.findById(requestID).get(); //TODO test for null

        Request newReq =
                Request.builder()
                        .senderID(request.getSenderID())
                        .recieverID(request.getRecieverID())
                        .sendDate(new Date())
                        .groupId(request.getGroupId())
                        .status(Status.Asking)
                        .build();

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }

    public List<Request> getRequestByGroupId(String groupId){
        return requestRepository.findByGroupId(groupId);
    }


}
