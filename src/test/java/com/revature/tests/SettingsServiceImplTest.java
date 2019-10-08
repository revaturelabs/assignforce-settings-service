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
	
	/**
	 * Tests if groups of settings is properly persisted by SettingsService and getAll returns them all as expected.
	 */
	@Test
	public void getAllTest() {
		Settings s1 = new Settings(1, "SettingsOne", 4, 3, 10, 23, 10, 30, 2, 1, 1,   "Microservices");
		Settings s2 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,   "Database");
		Settings s3 = new Settings(5, "SettingsFive", 2, 3, 10, 23, 10, 30, 2, 1, 14,  "AWS");
		Settings s4 = new Settings(7, "SettingsSeven", 2, 3, 10, 23, 10, 30, 2, 1, 3,  ".NET");
		List<Settings> settingsList = new ArrayList<Settings>();
		settingsList.add(s1);
		settingsList.add(s2);
		settingsList.add(s3);
		settingsList.add(s4);
		Mockito.when(settingsRepository.findAll()).thenReturn(settingsList);
		List<Settings> testList = settingsService.getAll();
//		assertTrue(testList.size() == 4);
		assertEquals(4, testList.size());
		/*assertEquals(s1, settingsList.get(0)); //now why would you do this?
		assertEquals(s2, settingsList.get(1));
		assertEquals(s3, settingsList.get(2));
		assertEquals(s4, settingsList.get(3));*/
		
		assertEquals(s1, testList.get(0));
		assertEquals(s2, testList.get(1));
		assertEquals(s3, testList.get(2));
		assertEquals(s4, testList.get(3));
	}
	
	/**
	 * Tests if SettingService findById() returns expected setting.
	 */
	@Test
	public void findByIdTest() {
		Settings s1 = new Settings(1, "SettingsOne", 4, 3, 10, 23, 10, 30, 2, 1, 1,   "Microservices");
		Optional<Settings> op1 = Optional.of(s1);
		Mockito.when(settingsRepository.findById(s1.getId())).thenReturn(op1);
		Optional<Settings> opTest = settingsService.findById(s1.getId());
//		assertTrue(opTest.get().getId() == 1);
		assertEquals(s1.getId().intValue(), opTest.get().getId().intValue());
	}
	
	/**
	 * Tests if SettingService update() persists new alias as expected.
	 */
	@Test
	public void updateTestAlias() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,   "FullStack");
		s1.setAlias("Java");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
//		assertTrue(sTest.getId() == 6);
		assertEquals(s1.getAlias(), sTest.getAlias());
	}

	/**
	 * Tests if SettingService update() persists new trainers per page as expected.
	 */
	@Test
	public void updateTestTPPage() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,   "FullStack");
		s1.setTrainersPerPage(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getTrainersPerPage().intValue(), sTest.getTrainersPerPage().intValue());
	}

	/**
	 * Tests if SettingService update() persists new report on outgoing graduates as expected.
	 */
	@Test
	public void updateTestRepGrads() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setReportOutgoingGrads(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getReportOutgoingGrads().intValue(), sTest.getReportOutgoingGrads().intValue());
	}

	/**
	 * Tests if SettingService update() persists new batch length as expected.
	 */
	@Test
	public void updateTestBatchLength() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setBatchLength(12);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getBatchLength().intValue(), sTest.getBatchLength().intValue());
	}

	/**
	 * Tests if SettingService update() persists new report on incoming candidates as expected.
	 */
	@Test
	public void updateTestRepIncGrade() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setReportIncomingCandidates(22);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getReportIncomingCandidates().intValue(), sTest.getReportIncomingCandidates().intValue());
	}

	/**
	 * Tests if SettingService update() persists new minimum batch size as expected.
	 */
	@Test
	public void updateTestMinBS() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setMinBatchSize(8);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getMinBatchSize().intValue(), sTest.getMinBatchSize().intValue());
	}

	/**
	 * Tests if SettingService update() persists new maximum batch size as expected.
	 */
	@Test
	public void updateTestMaxBS() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setMaxBatchSize(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getMaxBatchSize().intValue(), sTest.getMaxBatchSize().intValue());
	}

	/**
	 * Tests if SettingService update() persists new trainer break days as expected.
	 */
	@Test
	public void updateTestTBreakDays() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
		s1.setTrainerBreakDays(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getTrainerBreakDays().intValue(), sTest.getTrainerBreakDays().intValue());
	}

	/**
	 * Tests if SettingService update() persists new default location as expected.
	 */
	@Test
	public void updateTestDefLoc() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		s1.setDefaultLocation(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getDefaultLocation().intValue(), sTest.getDefaultLocation().intValue());
	}

	/**
	 * Tests if SettingService update() persists new default building as expected.
	 */
	@Test
	public void updateTestDefBld() {
		Settings s1 = new Settings(6, "SettingsSix", 4, 3, 10, 23, 10, 30, 2, 1, 1,   "FullStack");
		s1.setDefaultBuilding(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.update(s1);
		assertEquals(s1.getDefaultBuilding().intValue(), sTest.getDefaultBuilding().intValue());
	}
	
	/**
	 * Tests SettingService create() persists new setting as expected.
	 */
	@Test
	public void createTest() {
		Settings s1 = new Settings(7, "SettingsSeven", 4, 3, 10, 23, 10, 30, 2, 1, 1,  "FullStack");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		Settings sTest = settingsService.create(s1);
//		assertTrue(sTest.getId() == 7);
		assertEquals(s1.getId().intValue(), sTest.getId().intValue());
	}
	
	/**
	 * Tests SettingService delete() removes setting as expected.
	 */
	@Test
	public void deleteTest() {
        Settings s1 = new Settings(4, "SettingsSeven", 4, 3, 10, 23, 10, 30, 2, 1, 1, "FullStack");
        Mockito.doNothing().when(settingsRepository).deleteById(4);
        settingsService.delete(4);
		Optional<Settings> opTest = settingsService.findById(4);
		assertFalse(opTest.isPresent());
	}

}