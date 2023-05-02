package com.bme.onlab.requestservice;


import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EntityScan(basePackageClasses = {Request.class})
@EnableFeignClients(basePackageClasses = {UserApi.class, SchoolApi.class})
public class RequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);


	}
}
