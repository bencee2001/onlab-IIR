package com.bme.onlab.user_service_api.controller_interface;

import com.bme.onlab.errors.NoSuchRoleException;
import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.user_service_api.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="user" , url="${feign.user.url}")
public interface UserApi {

    @GetMapping("/user/listusers")
    List<UserDto> listAll();

    @GetMapping("/user/listusersbyschool/{schoolId}")
    List<UserDto> listAllBySchool(@PathVariable Integer schoolId) throws NoSuchSchoolException;

    @GetMapping("/user/getuser/{id}")
    UserDto getUserById(@PathVariable Integer id) throws NoSuchUserException;

    @GetMapping("/user/getteacherprincipal/{schoolId}")
    List<UserDto> getTeacherAndPrincipalBySchoolId(@PathVariable Integer schoolId);

    @GetMapping("/user/userrequests/{id}")
    UserRequestDTO getUserRequests(@PathVariable Integer id) throws NoSuchUserException;

    @PostMapping("/user/createUser")
    UserDto createUser(@RequestBody UserCreateObject user);

    @DeleteMapping("/user/delete/{id}")
    void delete(@PathVariable Integer id);

    @PutMapping("/user/update")
    UserDto updateUser(@RequestBody UserUpdateObject userData) throws NoSuchUserException;

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
