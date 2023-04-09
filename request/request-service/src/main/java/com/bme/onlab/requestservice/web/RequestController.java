package com.bme.onlab.requestservice.web;

import com.bme.onlab.errors.AlreadyAnsweredRequestException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestservice.service.RequestService;
import com.bme.onlab.requestserviceapi.controller_interface.RequestApi;
import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.CreateRequestObject;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController implements RequestApi {

    @Autowired
    RequestService requestService;

    @Autowired
    UserApi userApi;

    @Autowired
    SchoolApi schoolApi;

    @Autowired
    KafkaTemplate<String, KafkaRequestObject> kafkaTemplate; //TODO kafka

    @Override
    public void createRequestGroup(@RequestBody CreateRequestObject requestCreateObject) throws NoSuchUserException {
        String newUUID = requestService.createGroup(requestCreateObject);
        userApi.addRequestGroupId(requestCreateObject.getSenderID(),newUUID);
        userApi.addRequestGroupId(requestCreateObject.getRecieverID(),newUUID);
    }

    @Override
    public void createAnswerToGroup(String groupId, String message) throws AlreadyAnsweredRequestException { // TODO tesztelni adott user kuldo vagy fogado
        requestService.createRequest(groupId,message);
    }

    @Override
    public List<Request> getRequestsByGroupId(String groupId) { // TODO csak az kerhesse le aki benne van a requestbe
        return requestService.getRequestByGroupId(groupId);
    }

    @Override
    public void deleteRequestGroup(String groupId) {
        requestService.deleteRequestGroup(groupId);
    }

    @Override
    public void acceptRequest(String groupId) throws AlreadyAnsweredRequestException {
        requestService.acceptRequest(groupId);
        /*
            BigDecimal val = requestService.acceptRequest(groupId);
            schoolApi.
        * */
    }

    @Override
    public void rejectRequest(String groupId) throws AlreadyAnsweredRequestException {
        requestService.rejectRequest(groupId);
    }

    @Override
    public void sendKafka(KafkaRequestObject obj) {
        kafkaTemplate.send("newRequestGroup", obj);
    }
}