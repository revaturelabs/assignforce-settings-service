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
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.repos.SettingsRepository;
import com.revature.assignforce.service.SettingsService;
import com.revature.assignforce.service.SettingsServiceImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SettingsServiceImplTest {
	
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
	}
	
	@Autowired
	private SettingsService settingsService;
	@Autowired
	private SettingsRepository settingsRepository;
	

	@Test
	public void getAllTest() {
		Settings s1 = new Settings(1, "SettingsOne", 4, 3, 10, 23, 10, 30, 2, 1, 1, "Microservices");
		Settings s2 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2, "Database");
		Settings s3 = new Settings(5, "SettingsFive", 2, 3, 10, 23, 10, 30, 2, 1, 14, "AWS");
		Settings s4 = new Settings(7, "SettingsSeven", 2, 3, 10, 23, 10, 30, 2, 1, 3, ".NET");
		List<Settings> settingsList = new ArrayList<Settings>();
		settingsList.add(s1);
		settingsList.add(s2);
		settingsList.add(s3);
		settingsList.add(s4);
		Mockito.when(settingsRepository.findAll()).thenReturn(settingsList);
		List<Settings> testList = settingsService.getAll();
		assertTrue(testList.size() == 4);
	}
	
	@Test
	public void findByIdTest() {
		Settings s1 = new Settings(1, "SettingsOne", 4, 3, 10, 23, 10, 30, 2, 1, 1, "Microservices");
		Optional<Settings> op1 = Optional.ofNullable(s1);
		Mockito.when(settingsRepository.findById(1)).thenReturn(op1);
		Optional<Settings> opTest = settingsService.findById(1);
		assertTrue(opTest.get().getId() == 1);
	}
	
	@Test
	public void updateTest() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertTrue(sTest.getId() == 6);
	}
	
	@Test
	public void createTest() {
		Settings s1 = new Settings(7, "SettingsSeven", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.create(s1);
		assertTrue(sTest.getId() == 7);
	}
	
	@Test
	public void deleteTest() {
		Mockito.doNothing().when(settingsRepository).deleteById(4);
		settingsService.delete(4);
		Optional<Settings> opTest = settingsService.findById(4);
		assertFalse(opTest.isPresent());
	}

}
