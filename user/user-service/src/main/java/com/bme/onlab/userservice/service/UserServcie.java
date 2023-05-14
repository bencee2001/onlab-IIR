package com.bme.onlab.userservice.service;

import com.bme.onlab.errors.NoSuchRoleException;
import com.bme.onlab.errors.NoSuchUserException;
import com.bme.onlab.user_service_api.model.*;
import com.bme.onlab.userservice.repositroy.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServcie {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDto createUser(UserCreateObject user){
        User.UserBuilder userBuild = User.builder()
                .name(user.getName())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(Role.ROLE_STUDENT)
                .schoolID(user.getSchoolID())
                .classID(null);
        return modelMapper.map(userRepository.save(userBuild.build()),UserDto.class);
    }

    public void deleteUser(Integer id){
        userRepository.deleteById(id);
    }

    public void changeSchool(Integer userId,Integer newSchoolId) throws NoSuchUserException {
        User user = getUserById(userId);   // TODO school check
        user.setSchoolID(newSchoolId);
        userRepository.save(user);
    }

    public void changeRole(Integer userId, Role newRole) throws NoSuchUserException, NoSuchRoleException {
        User user = getUserById(userId);
        if(checkRoleExists(newRole)) {
            user.setRole(newRole);
            userRepository.save(user);
        }
    }

    public void changeClass(Integer userId, Integer classId) throws NoSuchUserException {
        User user = getUserById(userId);
        user.setClassID(classId);
        userRepository.save(user);
    }

    public List<UserDto> listUsers(){
        return userRepository.findAll()
                .stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    public void addGroupId(Integer userId, String requestGroupId) throws NoSuchUserException {
        User user = getUserById(userId);
        user.addNewGroupId(requestGroupId);
        userRepository.save(user);
    }

    public User getUserById(Integer id) throws NoSuchUserException {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            return user.get();
        }
        throw new NoSuchUserException("No user by the id of " + id);
    }

    public UserDto getUserDtoById(Integer id) throws NoSuchUserException {
        return modelMapper.map(getUserById(id),UserDto.class);
    }

    private Boolean checkRoleExists(Role checkingRole) throws NoSuchRoleException {
        Role[] roles = Role.values();
        for (Role role : roles) {
            if (role.equals(checkingRole))
                return true;
        }
        throw new NoSuchRoleException(checkingRole.toString());
    }

    public List<String> getGroupIdsByUser(Integer userId) throws NoSuchUserException {
        User user = getUserById(userId);
        return user.getRequestGroupIDs();
    }

    public void deleteGroupIdFromUser(Integer userId, String requestGroupId) throws NoSuchUserException {
        User user = getUserById(userId);
        List<String> copyList = user.getRequestGroupIDs().stream().filter(e -> !e.equals(requestGroupId)).collect(Collectors.toList());
        System.out.println("---------------------");
        System.out.println(copyList);
        user.setRequestGroupIDs(copyList);
        userRepository.save(user);
    }

    public List<UserDto> getUsersBySchool(Integer schoolId) {
        return userRepository.findAllBySchoolID(schoolId)
                .stream().map(user -> modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
    }

    public UserDto updateUser(UserUpdateObject userData) throws NoSuchUserException {
        User user = getUserById(userData.getId());
        user.setName(userData.getName());
        user.setRole(userData.getRole());
        user.setClassID(userData.getClassId());
        return modelMapper.map(userRepository.save(user),UserDto.class);
    }

    public UserRequestDTO getUserRequestsByID(Integer id) throws NoSuchUserException {
        return modelMapper.map(getUserById(id),UserRequestDTO.class);
    }

    public List<UserDto> getTeacherAndPrincipal(Integer schoolId) {
        return getUsersBySchool(schoolId)
                .stream()
                .filter(userDto -> userDto.getRole()==Role.ROLE_TEACHER || userDto.getRole()==Role.ROLE_PRINCIPAL)
                .toList();
    }
}
