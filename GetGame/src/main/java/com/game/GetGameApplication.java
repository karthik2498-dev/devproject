package com.game;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GetGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(GetGameApplication.class, args);
	}

}
