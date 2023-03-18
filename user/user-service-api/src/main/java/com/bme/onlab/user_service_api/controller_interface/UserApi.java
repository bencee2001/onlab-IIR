package com.bme.onlab.user_service_api.controller_interface;

import com.bme.onlab.user_service_api.model.Role;
import com.bme.onlab.user_service_api.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface UserApi {


    @GetMapping("/createUser")
    void createUser(String name, String username, String password, Role role, Integer schoolId, Integer classId);

    @PostMapping("/delete/{id}")
    void delete(@PathVariable Integer id);

    @PostMapping("/newRole/{id}")
    void setNewRole(@PathVariable Integer id, Role newRole);

    @GetMapping("/listAll")
    List<User> listAll();
}
