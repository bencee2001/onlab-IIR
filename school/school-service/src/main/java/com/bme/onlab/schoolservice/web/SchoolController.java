package com.bme.onlab.schoolservice.web;

import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.schoolservice.service.SchoolService;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.schoolserviceapi.model.School;
import io.swagger.v3.core.util.Json;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class SchoolController implements SchoolApi {

    private final SchoolService schoolService;

    @Override
    public List<School> listAll() {
        return schoolService.listSchools();
    }

    @Override
    public School getSchoolById(Integer id) throws NoSuchSchoolException {
        return schoolService.getSchoolById(id);
    }

    @Override
    public School addToBudget(Integer id, BigDecimal amount) throws NoSuchSchoolException {
        return schoolService.increaseBudgetBy(id,amount);
    }

    @Override
    public String getSchoolName(Integer id) throws NoSuchSchoolException {
        return Json.pretty(schoolService.getSchoolName(id));
    }
}
