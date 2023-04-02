package com.bme.onlab.userservice.service;

import com.bme.onlab.user_service_api.model.Role;
import com.bme.onlab.user_service_api.model.User;
import com.bme.onlab.userservice.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServcie {

    private final UserRepository userRepository;

    public void createUser(String name, String username, String password, Role role,Integer schoolId,Integer classId){
        User.UserBuilder userBuild = User.builder()
                .name(name)
                .username(username)
                .password(password)
                .role(role)
                .schoolID(schoolId);
        if(classId!=null) userBuild.classID(classId);
        userRepository.save(userBuild.build());
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void changeSchool(Integer userId,Integer newSchoolId){
        User user = userRepository.getReferenceById(userId);
        //TODO check for school existing
        user.setSchoolID(newSchoolId);
        userRepository.save(user);
    }

    public void changeRole(Integer userId, Role newRole){
        User user = userRepository.getReferenceById(userId);
        user.setRole(newRole);
        userRepository.save(user);
    }

    public List<User> listUsers(){
        return userRepository.findAll();
    }

    public void addGroupId(Integer userId, String requestGroupId){
        User user = userRepository.findById(userId).get();//TODO null check
        user.addNewGroupId(requestGroupId);
        userRepository.save(user);
    }
}
