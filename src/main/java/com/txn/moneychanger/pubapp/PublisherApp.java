package com.txn.moneychanger.pubapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@EnableSwagger2
@SpringBootApplication
public class PublisherApp {
    private static final Logger logger = LogManager.getLogger(PublisherApp.class);

	public static void main(String[] args) {
		SpringApplication.run(PublisherApp.class, args);
	}

}
