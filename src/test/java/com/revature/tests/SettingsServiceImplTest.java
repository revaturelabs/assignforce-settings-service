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
//		assertTrue(testList.size() == 4);
		assertEquals(4, testList.size());
		assertEquals(s1, settingsList.get(0));
		assertEquals(s2, settingsList.get(1));
		assertEquals(s3, settingsList.get(2));
		assertEquals(s4, settingsList.get(3));
	}
	
	@Test
	public void findByIdTest() {
		Settings s1 = new Settings(1, "SettingsOne", 4, 3, 10, 23, 10, 30, 2, 1, 1, "Microservices");
		Optional<Settings> op1 = Optional.of(s1);
		Mockito.when(settingsRepository.findById(s1.getId())).thenReturn(op1);
		Optional<Settings> opTest = settingsService.findById(s1.getId());
//		assertTrue(opTest.get().getId() == 1);
		assertEquals(s1.getId().intValue(), opTest.get().getId().intValue());
	}
	
	@Test
	public void updateTestAlias() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setAlias("Java");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
//		assertTrue(sTest.getId() == 6);
		assertEquals(s1.getAlias(), sTest.getAlias());
	}

	@Test
	public void updateTestTPPage() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setTrainersPerPage(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getTrainersPerPage().intValue(), sTest.getTrainersPerPage().intValue());
	}

	@Test
	public void updateTestRepGrads() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setReportGrads(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getReportGrads().intValue(), sTest.getReportGrads().intValue());
	}

	@Test
	public void updateTestBatchLength() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setBatchLength(12);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getBatchLength().intValue(), sTest.getBatchLength().intValue());
	}

	@Test
	public void updateTestRepIncGrade() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setReportIncomingGrads(22);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getReportIncomingGrads().intValue(), sTest.getReportIncomingGrads().intValue());
	}

	@Test
	public void updateTestMinBS() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setMinBatchSize(8);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getMinBatchSize().intValue(), sTest.getMinBatchSize().intValue());
	}

	@Test
	public void updateTestMaxBS() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setMaxBatchSize(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getMaxBatchSize().intValue(), sTest.getMaxBatchSize().intValue());
	}

	@Test
	public void updateTestTBreakDays() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setTrainerBreakDays(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getTrainerBreakDays().intValue(), sTest.getTrainerBreakDays().intValue());
	}

	@Test
	public void updateTestDefLoc() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setDefaultLocation(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getDefaultLocation().intValue(), sTest.getDefaultLocation().intValue());
	}

	@Test
	public void updateTestDefBld() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setDefaultBuilding(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getDefaultBuilding().intValue(), sTest.getDefaultBuilding().intValue());
	}
	
	@Test
	public void createTest() {
		Settings s1 = new Settings(7, "SettingsSeven", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.create(s1);
//		assertTrue(sTest.getId() == 7);
		assertEquals(s1.getId().intValue(), sTest.getId().intValue());
	}
	
	@Test
	public void deleteTest() {
        Settings s1 = new Settings(4, "SettingsSeven", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
        Mockito.doNothing().when(settingsRepository).deleteById(4);
        settingsService.delete(4);
		Optional<Settings> opTest = settingsService.findById(4);
		assertFalse(opTest.isPresent());
	}

}