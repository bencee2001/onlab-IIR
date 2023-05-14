package com.bme.onlab.userservice.web;

import com.bme.onlab.errors.NoSuchRoleException;
import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestserviceapi.controller_interface.RequestApi;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.schoolserviceapi.model.School;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import com.bme.onlab.user_service_api.model.*;
import com.bme.onlab.userservice.service.UserServcie;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@AllArgsConstructor
public class UserController implements UserApi {


    private final UserServcie userServcie;

    private final RequestApi requestApi;

    private final SchoolApi schoolApi;


    @Override
    public List<UserDto> listAll() {
        List<UserDto> s = userServcie.listUsers();
        for(UserDto a : s)
            System.out.println(a.getId());
        return  s;
    }

    @Override
    public List<UserDto> listAllBySchool(Integer schoolId) throws NoSuchSchoolException {
        School school = schoolApi.getSchoolById(schoolId);
        return userServcie.getUsersBySchool(schoolId);
    }

    @Override
    public UserDto getUserById(Integer id) throws NoSuchUserException {
        return userServcie.getUserDtoById(id);
    }

    @Override
    public List<UserDto> getTeacherAndPrincipalBySchoolId(Integer schoolId) {
        return userServcie.getTeacherAndPrincipal(schoolId);
    }

    @Override
    public UserRequestDTO getUserRequests(Integer id) throws NoSuchUserException {
        System.out.println("hello");
        return userServcie.getUserRequestsByID(id);
    }

    @Override
    public UserDto createUser(UserCreateObject user) {
        return userServcie.createUser(user);
    }

    @Override
    public void delete(Integer id) {
        userServcie.deleteUser(id);
    }

    @Override
    public UserDto updateUser(UserUpdateObject userData) throws NoSuchUserException {
        return userServcie.updateUser(userData);
    }

    @Override
    public void setNewRole(Integer id, Role newRole) throws NoSuchUserException, NoSuchRoleException { //TODO letezik-e role
        userServcie.changeRole(id, newRole);
    }

    @Override
    public void setClass(Integer id, Integer classId) throws NoSuchUserException {  //TODO osztaly letezik-e
        userServcie.changeClass(id,classId);
    }

    @Override
    public void setSchool(Integer id, Integer schoolId) throws NoSuchUserException { //TODO iskola letezik-e
        userServcie.changeSchool(id, schoolId);
    }

    @Override
    public void addRequestGroupId(Integer id, String groupId) throws NoSuchUserException {
        userServcie.addGroupId(id, groupId);
    }

    @Override
    public List<List<Request>> listRequestGroups(Integer userId) throws NoSuchUserException {
        List<String> groupIds = userServcie.getGroupIdsByUser(userId);
        List<List<Request>> requestGroups = new ArrayList<>();
        groupIds.forEach( (groupId) ->{
            requestGroups.add(requestApi.getRequestsByGroupId(groupId));
        });
        return requestGroups;
    }

    @Override
    public void removeRequestGroupId(Integer userId, String requestGroupId) throws NoSuchUserException {
        userServcie.deleteGroupIdFromUser(userId, requestGroupId);
    }


}
