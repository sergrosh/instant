package com.instant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

/**
 * @author sroshchupkin
 */
@SpringBootApplication
public class InstantApplication {

	public static void main(String[] args) {
		SpringApplication.run(InstantApplication.class, args);
	}
}
