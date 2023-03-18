package com.bme.onlab.schoolservice.repository;

import com.bme.onlab.schoolserviceapi.model.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SchoolRepository extends JpaRepository<School,Integer> {
}
