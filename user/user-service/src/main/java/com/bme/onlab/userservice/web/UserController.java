package com.bme.onlab.userservice.web;

import com.bme.onlab.errors.NoSuchRoleException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.requestserviceapi.controller_interface.RequestApi;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import com.bme.onlab.user_service_api.model.User;
import com.bme.onlab.user_service_api.model.UserCreateObject;
import com.bme.onlab.userservice.service.UserServcie;
import com.bme.onlab.user_service_api.model.Role;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController implements UserApi {

    @Autowired
    private UserServcie userServcie;

    @Autowired
    private RequestApi requestApi;


    @Override
    public List<User> listAll() {
        return userServcie.listUsers();
    }

    @Override
    public User getUserById(Integer id) throws NoSuchUserException {
        return userServcie.getUserById(id);
    }

    @Override
    public void createUser(UserCreateObject user) {
        userServcie.createUser(user);
    }

    @Override
    public void delete(Integer id) {
        userServcie.deleteUser(id);
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
