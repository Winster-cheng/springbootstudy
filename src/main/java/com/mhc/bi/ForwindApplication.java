package com.mhc.bi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.baiyan.forwind.mapper")
public class ForwindApplication {
	public static void main(String[] args) {
		SpringApplication.run(ForwindApplication.class, args);
		System.out.println("Hello world");
	}
}
