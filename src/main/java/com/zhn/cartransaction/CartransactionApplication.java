package com.zhn.cartransaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhn.cartransaction.mapper")
public class CartransactionApplication {

	public static void main(String[] args) {
		SpringApplication.run(CartransactionApplication.class, args);
	}

}
