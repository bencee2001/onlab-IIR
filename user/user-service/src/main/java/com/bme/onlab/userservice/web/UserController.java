package com.bme.onlab.userservice.web;

import com.bme.onlab.user_service_api.controller_interface.UserApi;
import com.bme.onlab.user_service_api.model.User;
import com.bme.onlab.userservice.service.UserServcie;
import lombok.RequiredArgsConstructor;
import com.bme.onlab.user_service_api.model.Role;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserServcie userServcie;

    @Override
    public void createUser() {

    }

    @Override
    public void delete(Integer id) {

    }

    @Override
    public void setNewRole(Integer id, Role newRole) {

    }

    @Override
    public List<User> listAll() {
        return userServcie.listUsers();
    }

    @Override
    public void addRequestGroupId(Integer userId,String groupId) {
        userServcie.addGroupId(userId,groupId);
    }


    //TODO Listener
}
