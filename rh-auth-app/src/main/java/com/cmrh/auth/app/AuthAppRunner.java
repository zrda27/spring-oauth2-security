package com.cmrh.auth.app;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class AuthAppRunner {

	public static void main(String[] args) {
		new SpringApplicationBuilder(AuthAppRunner.class).run(args);
	}

}