package com.revature.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.controllers.SettingsController;
import com.revature.assignforce.repos.SettingsRepository;
import com.revature.assignforce.service.SettingsService;
import com.revature.assignforce.service.SettingsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SettingsControllerTest {

	@Configuration
	static class SettingsServiceTestContextConfiguration {
	@Bean
	public SettingsService SettingsService() {
		return new SettingsServiceImpl();
		}
	@Bean
	public SettingsRepository SettingsRepository() {
		return Mockito.mock(SettingsRepository.class);
		}
	@Bean
	public SettingsController SettingsController() {
		return new SettingsController();
		}
	}
	
	@Autowired
	private SettingsService settingsService;
	@Autowired
	private SettingsRepository settingsRepository;
	@Autowired
	private SettingsController settingsController;
	
	@Test
	public void getByIdTestOK() {
		Settings s1 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2, "Database");
		Optional<Settings> op1 = Optional.ofNullable(s1);
		Mockito.when(settingsRepository.findById(3)).thenReturn(op1);
		ResponseEntity<Settings> reTest = settingsController.getById(3);
		assertTrue(reTest.getBody().getId() == 3 && reTest.getStatusCode() == HttpStatus.OK);
	}
	
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Settings> reTest = settingsController.getById(9);
		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void updateTestCreate() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2, "Database");
		s1.setAlias("Java");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertTrue(reTest.getBody().getAlias().equals("Java") && reTest.getStatusCode() == HttpStatus.CREATED);
	}
	
	@Test
	public void updateTestBadRequest() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2, "Database");
		s1.setAlias("C++");
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
	}
	
}
