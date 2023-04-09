package com.bme.onlab.requestservice.service;

import com.bme.onlab.errors.AlreadyAnsweredRequestException;
import com.bme.onlab.requestservice.repository.RequestRepository;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.CreateRequestObject;
import com.bme.onlab.requestserviceapi.model.Status;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RequestService {

    final private RequestRepository requestRepository;

    public String createGroup(CreateRequestObject requestDTO){
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

    public void createRequest(String groupId, String message) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq = makeNewRequest(request,Status.Asking);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }


    public void acceptRequest(String groupId) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq=makeNewRequest(request,Status.Accepted);

        request.setStatus(Status.Answered);
        requestRepository.save(request);
        requestRepository.save(newReq);
    }

    public void rejectRequest(String groupId) throws AlreadyAnsweredRequestException {
        Request request = checkRequestGroup(groupId);
        if (request == null) return;

        Request newReq=makeNewRequest(request,Status.Rejected);

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

    private static Request makeNewRequest(Request request, Status status) {
        return Request.builder()
                .senderID(request.getSenderID())
                .recieverID(request.getRecieverID())
                .sendDate(new Date())
                .groupId(request.getGroupId())
                .status(status)
                .build();
    }
}
