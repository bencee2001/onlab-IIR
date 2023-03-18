package com.bme.onlab.schoolservice.service;

import com.bme.onlab.schoolservice.repository.SchoolRepository;
import com.bme.onlab.schoolserviceapi.model.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    final private SchoolRepository schoolRepository;

    public void increaseBudgetBy(BigDecimal value, Integer id){
        School school = schoolRepository.findById(id).get();
        BigDecimal schoolBudget = school.getBudget();
        school.setBudget(schoolBudget.add(value));
        schoolRepository.save(school);
    }

    public List<School> listSchools(){
        return schoolRepository.findAll();
    }
}
