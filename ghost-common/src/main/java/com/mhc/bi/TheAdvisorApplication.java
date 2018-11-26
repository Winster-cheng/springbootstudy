package com.mhc.bi;

import com.mhc.bi.common.hadoop.util.SpringContextUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.mhc.bi.mapper")
public class TheAdvisorApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TheAdvisorApplication.class, args);
		SpringContextUtil.setApplicationContextutil(context);
	}

}
