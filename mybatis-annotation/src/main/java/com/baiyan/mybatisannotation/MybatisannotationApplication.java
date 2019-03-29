package com.baiyan.mybatisannotation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.baiyan.mybatisannotation.dal.dao")
public class MybatisannotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MybatisannotationApplication.class, args);
	}

}
