package com.bme.onlab.requestservice.service;

import com.bme.onlab.errors.AlreadyAnsweredRequestException;
import com.bme.onlab.requestservice.repository.RequestRepository;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.CreateRequestObject;
import com.bme.onlab.requestserviceapi.model.Status;
import com.bme.onlab.requestserviceapi.model.UserIdAndPrice;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {

    final private RequestRepository requestRepository;

    public String createGroup(CreateRequestObject CRO){
        String uuid = UUID.randomUUID().toString();
        Request request =
                Request.builder()
                        .senderID(CRO.getSenderID())
                        .recieverID(CRO.getRecieverID())
                        .sendDate(new Date())
                        .message(CRO.getMessage())
                        .groupId(uuid)
                        .status(Status.Asking)
                        .price(CRO.getPrice())
                        .build();

        System.out.println("----------------");
        System.out.println(request.getPrice());
        requestRepository.save(request);
        return uuid;
    }

    public void createRequest(String groupId, String message) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq = makeNewRequest(request,message,Status.Asking);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }


    public UserIdAndPrice acceptRequest(String groupId, String message) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return null;

        Request newReq=makeNewRequest(request,message,Status.Accepted);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
        return new UserIdAndPrice(newReq.getSenderID(), newReq.getPrice());
    }

    public void counterRequest(String groupId, String message, BigDecimal newPrice) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq=makeNewRequest(request,message,Status.Asking);
        newReq.setPrice(newPrice);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }

    public void rejectRequest(String groupId, String  message) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq=makeNewRequest(request,message,Status.Rejected);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }

    public List<Request> getRequestByGroupId(String groupId){
        return requestRepository.findByGroupIdOrderBySendDate(groupId);
    }


    public void deleteRequestGroup(String groupId) {
        List<Request> group = requestRepository.findByGroupIdOrderBySendDate(groupId);
        group.forEach(request -> {
            requestRepository.deleteById(request.getId());
        });
    }

    public List<Request> getAllRequest() {
        return requestRepository.findAll();
    }

    public List<String> getAllGroupIds(){
        return requestRepository.findGroupIds();
    }

    private Request checkRequestGroup(String groupId) throws AlreadyAnsweredRequestException {
        List<Request> requestList = requestRepository.findByGroupIdOrderBySendDate(groupId);
        Request request = null;
        if(requestList.isEmpty())
            return null;

        request=requestList.get(requestList.size()-1);

        if(request.getStatus()!=Status.Asking){
            throw new AlreadyAnsweredRequestException();
        }
        return request;
    }

    private static Request makeNewRequest(Request request,String message, Status status) {
        return Request.builder()
                .senderID(request.getRecieverID())
                .recieverID(request.getSenderID())
                .sendDate(new Date())
                .message(message)
                .groupId(request.getGroupId())
                .status(status)
                .price(request.getPrice())
                .build();
    }
}
