package com.bme.onlab.schoolservice.service;

import com.bme.onlab.schoolservice.repository.SchoolRepository;
import com.bme.onlab.schoolserviceapi.model.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class SchoolService {

    final private SchoolRepository schoolRepository;

    public void increaseBudgetBy(Integer value, Integer id){
        School school = schoolRepository.findById(id).get();
        BigDecimal schoolBudget = school.getBudget();
        school.setBudget(schoolBudget.add(BigDecimal.valueOf(value)));
        schoolRepository.save(school);
    }
}
