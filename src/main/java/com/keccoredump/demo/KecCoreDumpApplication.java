package com.keccoredump.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class KecCoreDumpApplication {

	public static void main(String[] args) {
		SpringApplication.run(KecCoreDumpApplication.class, args);

	}

}
