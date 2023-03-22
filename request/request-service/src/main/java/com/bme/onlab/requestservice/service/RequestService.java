package com.bme.onlab.requestservice.service;

import com.bme.onlab.requestservice.repository.RequestRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestService {

    final private RequestRepository requestRepository;

    public void createRequest(){

    }
}
