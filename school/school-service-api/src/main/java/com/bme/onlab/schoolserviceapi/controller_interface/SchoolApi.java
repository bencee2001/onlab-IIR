package com.bme.onlab.schoolserviceapi.controller_interface;

import com.bme.onlab.schoolserviceapi.model.School;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/school")
public interface SchoolApi {

    @GetMapping("/listschools")
    public List<School> listAll();
}
