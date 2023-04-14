package com.bme.onlab.requestservice;


import com.bme.onlab.kafka.model.KafkaRequestObject;
import com.bme.onlab.requestserviceapi.model.Request;
import com.bme.onlab.schoolserviceapi.controller_interface.SchoolApi;
import com.bme.onlab.user_service_api.controller_interface.UserApi;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;


@SpringBootApplication
@EntityScan(basePackageClasses = {Request.class})
@EnableFeignClients(basePackageClasses = {UserApi.class, SchoolApi.class})
public class RequestServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(RequestServiceApplication.class, args);


	}

	/*@Bean
	CommandLineRunner clr(KafkaTemplate<String, KafkaRequestObject> kafkaTemplate){
		return args ->{
			kafkaTemplate.send("newRequestGroup",
					KafkaRequestObject.builder()
							.requestGroupID("1000")
							.receiverID(100)
							.senderID(200)
							.build());
		};
	}*/

}
