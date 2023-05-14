package com.bme.onlab.schoolservice.service;

import com.bme.onlab.errors.NoSuchSchoolException;
import com.bme.onlab.schoolservice.repository.SchoolRepository;
import com.bme.onlab.schoolserviceapi.model.School;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchoolService {

    final private SchoolRepository schoolRepository;

    public School increaseBudgetBy(Integer id,BigDecimal value) throws NoSuchSchoolException {
        School school = getSchoolById(id);
        BigDecimal schoolBudget = school.getBudget();
        school.setBudget(schoolBudget.add(value));
        return schoolRepository.save(school);
    }

    public List<School> listSchools(){
        return schoolRepository.findAll();
    }

    public School getSchoolById(Integer id) throws NoSuchSchoolException {
        Optional<School> school = schoolRepository.findById(id);
        if(school.isPresent())
            return school.get();
        throw new NoSuchSchoolException();
    }

    public String getSchoolName(Integer id) throws NoSuchSchoolException {
        return getSchoolById(id).getSchoolName();
    }
}
