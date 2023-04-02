package com.bme.onlab.requestserviceapi.controller_interface;

import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.RequestDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/request")
public interface RequestApi {

    @PostMapping("/creategroup")
    void createRequestGroup(@RequestBody RequestDTO requestDTO);

    @PostMapping("/createrequest")
    void createRequest(Integer requestId, String message);

    @GetMapping("/getrequests")
    List<Request> getRequestsByGroupId(String groupId);
}
