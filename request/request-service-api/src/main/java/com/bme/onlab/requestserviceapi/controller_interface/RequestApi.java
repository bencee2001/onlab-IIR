package com.bme.onlab.requestserviceapi.controller_interface;

import com.bme.onlab.errors.AlreadyAnsweredRequestException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.CreateRequestObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "request", url = "${feign.request.url}")
public interface RequestApi {

    @PostMapping("/request/creategroup")
    void createRequestGroup(@RequestBody CreateRequestObject request) throws NoSuchUserException;

    @PostMapping("/request/createrequest/{groupId}")
    void createAnswerToGroup(@PathVariable String groupId, String message) throws AlreadyAnsweredRequestException;

    @GetMapping("/request/getrequests/{groupId}")
    List<Request> getRequestsByGroupId(@PathVariable("groupId") String id);

    @PostMapping("/request/delete/{groupId}")
    void deleteRequestGroup(@PathVariable String groupId);

    @PostMapping("/request/accept/{groupId}")
    void acceptRequest(@PathVariable String groupId) throws AlreadyAnsweredRequestException;

    @PostMapping("/request/reject/{groupId}")
    void rejectRequest(@PathVariable String groupId) throws AlreadyAnsweredRequestException;

    @PostMapping("/request/test")
    void sendKafka(@RequestBody KafkaRequestObject obj); // TODO kafka
}
