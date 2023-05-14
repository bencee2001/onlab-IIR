package com.bme.onlab.userservice.repositroy;

import com.bme.onlab.user_service_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    List<User> findAllBySchoolID(Integer schoolId);
}
