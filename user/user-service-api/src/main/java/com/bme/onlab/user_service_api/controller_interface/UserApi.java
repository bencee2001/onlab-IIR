package com.bme.onlab.user_service_api.controller_interface;

import com.bme.onlab.errors.NoSuchRoleException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.user_service_api.model.Role;
import com.bme.onlab.user_service_api.model.User;
import com.bme.onlab.user_service_api.model.UserCreateObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="user" , url="${feign.user.url}")
public interface UserApi {

    @GetMapping("/user/listusers")
    List<User> listAll();

    @GetMapping("/user/getuser/{id}")
    User getUserById(@PathVariable Integer id) throws NoSuchUserException;

    @PostMapping("/user/createUser")
    void createUser(@RequestBody UserCreateObject user);

    @PostMapping("/user/delete/{id}")
    void delete(@PathVariable Integer id);

    @PostMapping("/user/newrole/{id}")
    void setNewRole(@PathVariable Integer id,@RequestParam Role newRole) throws NoSuchUserException, NoSuchRoleException;

    @PostMapping("/user/newclass/{id}")
    void setClass(@PathVariable Integer id,@RequestParam Integer classId) throws NoSuchUserException;

    @PostMapping("/user/newschool/{id}")
    void setSchool(@PathVariable Integer id,@RequestParam Integer schoolId) throws NoSuchUserException;

    @PostMapping("/user/newgroupid/{id}")
    void addRequestGroupId(@PathVariable Integer id, @RequestParam String groupId) throws NoSuchUserException;

    @GetMapping("/user/listreqgroups/{userId}")
    List<List<Request>> listRequestGroups(@PathVariable Integer userId) throws NoSuchUserException;

    @PostMapping("/user/listreqgroups/{userId}/delreqgroup/{requestGroupId}")
    void removeRequestGroupId(@PathVariable Integer userId, @PathVariable String requestGroupId) throws NoSuchUserException;




}
