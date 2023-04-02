package com.bme.onlab.user_service_api.controller_interface;

import com.bme.onlab.user_service_api.model.Role;
import com.bme.onlab.user_service_api.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name="user" , url="${feign.user.url}")
public interface UserApi {

    @GetMapping("/createUser")
    void createUser();

    @PostMapping("/delete/{id}")
    void delete(@PathVariable Integer id);

    @PostMapping("/newRole/{id}")
    void setNewRole(@PathVariable Integer id, Role newRole);

    @GetMapping("/listusers")
    List<User> listAll();

    @PostMapping("newgroupid/{userId}")
    void addRequestGroupId(@PathVariable Integer userId, @RequestParam String groupId);

    //TODO listener
}
