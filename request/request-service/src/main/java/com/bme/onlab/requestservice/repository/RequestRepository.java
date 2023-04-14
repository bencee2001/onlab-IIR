package com.bme.onlab.requestservice.repository;

import com.bme.onlab.requestserviceapi.model.Request;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    List<Request> findByGroupIdOrderBySendDate(String groupId);

    @Query("SELECT distinct r.groupId FROM Request r")
    List<String> findGroupIds();
}
