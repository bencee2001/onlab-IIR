package com.bme.onlab.schoolservice.web;

import com.bme.onlab.schoolservice.service.SchoolService;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.schoolserviceapi.model.School;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController implements SchoolApi {

    private final SchoolService schoolService;

    @Override
    public List<School> listAll() {
        return schoolService.listSchools();
    }
}
