package com.revature.assignforce;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;


@EnableDiscoveryClient
@SpringBootApplication
public class SettingsServiceApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(SettingsServiceApplication.class).run(args);
	}
}
