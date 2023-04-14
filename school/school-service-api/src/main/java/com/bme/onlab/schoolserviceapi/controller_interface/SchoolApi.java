package com.bme.onlab.schoolserviceapi.controller_interface;

import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.schoolserviceapi.model.School;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@FeignClient(name = "school", url = "http://localhost:8080")
public interface SchoolApi {

    @GetMapping("/school/listschools")
    public List<School> listAll();

    @GetMapping("/school/getschool/{id}")
    public School getSchoolById(@PathVariable Integer id) throws NoSuchSchoolException;

    @PostMapping("/school/budgetmod/{id}")
    public void addToBudget(@PathVariable Integer id,@RequestParam BigDecimal amount) throws NoSuchSchoolException;
}
