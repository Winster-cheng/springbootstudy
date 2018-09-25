package com.mhc.bi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.sql.Connection;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.mhc.bi.mapper")
public class TheAdvisorApplication {
	public static void main(String[] args) {
		SpringApplication.run(TheAdvisorApplication.class, args);
		System.out.println("Hello world");
	}
}
