package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * SettingsServiceApplication creates a web application with resources that are protected by Spring Security
 *
 */
@SpringBootApplication
@EnableWebSecurity // creating a web application with resources that are protected by Spring Security.
@EnableSwagger2
public class SettingsServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SettingsServiceApplication.class).run(args);
	} // create a new instance of SpringApplicationBuilder with (SettingsServiceApplication.class) as parameters
}
