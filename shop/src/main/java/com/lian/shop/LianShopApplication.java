package com.lian.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;

@SpringBootApplication
@MapperScan("com.lian.shop.mapper")
public class LianShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(LianShopApplication.class, args);
	}

}


