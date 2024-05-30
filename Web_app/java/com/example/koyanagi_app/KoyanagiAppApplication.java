package com.example.koyanagi_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@RequiredArgsConstructor
public class KoyanagiAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KoyanagiAppApplication.class, args)
		.getBean(KoyanagiAppApplication.class);
	}

}
