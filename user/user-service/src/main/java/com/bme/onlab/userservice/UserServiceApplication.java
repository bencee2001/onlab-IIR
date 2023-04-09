package com.bme.onlab.userservice;

import com.bme.onlab.requestserviceapi.controller_interface.RequestApi;
import com.bme.onlab.user_service_api.model.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan(basePackageClasses = {User.class})
@EnableFeignClients(basePackageClasses = {RequestApi.class})
public class UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserServiceApplication.class, args);
	}

}
