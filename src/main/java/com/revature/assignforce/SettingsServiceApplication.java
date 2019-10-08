package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class SettingsServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SettingsServiceApplication.class).run(args);
	}
}
