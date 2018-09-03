package com.lgp.droolsdrt;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.lgp.droolsdrt.mapper")
public class DroolsDrtApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroolsDrtApplication.class, args);
	}
}
