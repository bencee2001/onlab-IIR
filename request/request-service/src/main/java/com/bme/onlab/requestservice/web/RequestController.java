package com.bme.onlab.requestservice.web;

import com.bme.onlab.requestservice.service.RequestService;
import com.bme.onlab.requestserviceapi.controller_interface.RequestApi;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.RequestDTO;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RequestController implements RequestApi {

    @Autowired
    RequestService requestService;

    @Autowired
    UserApi userApi;

    @Override
    public void createRequestGroup(@RequestBody RequestDTO requestDTO) {
        String newUUID = requestService.createGroup(requestDTO);
        userApi.addRequestGroupId(requestDTO.getSenderID(),newUUID);
        userApi.addRequestGroupId(requestDTO.getRecieverID(),newUUID);
    }

    @Override
    public void createRequest(Integer requestId, String message) { // TODO tesztelni adott user kuldo vagy fogado
        requestService.createRequest(requestId,message);
    }

    @Override
    public List<Request> getRequestsByGroupId(String groupId) { // TODO csak az kerhesse le aki benne van a requestbe
        return requestService.getRequestByGroupId(groupId);
    }
}