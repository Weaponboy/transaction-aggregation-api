package com.weaponboy.transaction_aggregation_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.io.IOException;

@EnableScheduling
@SpringBootApplication
public class TransactionAggregationApiApplication {

	static program application = new program();

	public static void main(String[] args){
		SpringApplication.run(TransactionAggregationApiApplication.class, args);
		application.run();
	}

}
