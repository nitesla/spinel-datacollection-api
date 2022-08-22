package com.spinel.datacollection.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.LinkedList;

@ComponentScan(basePackages = "com.spinel.framework")
@EntityScan(basePackages = {"com.spinel.datacollection.core.models"})
@EnableJpaRepositories({"com.spinel.datacollection.service.repositories","com.spinel.framework.repositories"})
@SpringBootApplication
@EnableScheduling
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);



	}

}
