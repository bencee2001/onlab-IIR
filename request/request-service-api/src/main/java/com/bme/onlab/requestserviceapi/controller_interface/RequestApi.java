package com.bme.onlab.requestserviceapi.controller_interface;

import com.bme.onlab.errors.AlreadyAnsweredRequestException;
import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.requestserviceapi.model.CreateRequestObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "request", url = "${feign.request.url}")
public interface RequestApi {

    @GetMapping("/request/getrequests/{groupId}")
    List<Request> getRequestsByGroupId(@PathVariable("groupId") String id);

    @GetMapping("/request/getrequests")
    List<Request> getRequests();

    @GetMapping("/request/getgroups")
    List<String> getGroups();

    @PostMapping("/request/creategroup")
    void createRequestGroup(@RequestBody CreateRequestObject request) throws NoSuchUserException;

    @PostMapping("/request/createrequest/{groupId}")
    void createAnswerToGroup(@PathVariable String groupId, @RequestParam String message) throws AlreadyAnsweredRequestException;

    @PostMapping("/request/delete/{groupId}")
    void deleteRequestGroup(@PathVariable String groupId);

    @PostMapping("/request/accept/{groupId}")
    void acceptRequest(@PathVariable String groupId, @RequestParam String message) throws AlreadyAnsweredRequestException, NoSuchUserException, NoSuchSchoolException;

    @PostMapping("/request/reject/{groupId}")
    void rejectRequest(@PathVariable String groupId, @RequestParam String message) throws AlreadyAnsweredRequestException;

    @PostMapping("/request/counter/{groupId}")
    void counterRequest(@PathVariable String groupId, @RequestParam String message, @RequestParam BigDecimal newPrice) throws AlreadyAnsweredRequestException;

}
