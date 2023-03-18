package com.bme.onlab.schoolservice;

import com.bme.onlab.schoolserviceapi.model.School;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackageClasses = {School.class})
public class SchoolServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(SchoolServiceApplication.class, args);
	}

}
