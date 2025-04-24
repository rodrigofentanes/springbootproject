package com.domain.springbootproject;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.domain.springbootproject.core.Exemples;

@SpringBootApplication
public class SpringbootprojectApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootprojectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Exemples.getInstance().exemple3();
	}
}
