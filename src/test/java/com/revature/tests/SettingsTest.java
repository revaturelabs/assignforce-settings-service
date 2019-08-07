package com.revature.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
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
public class SettingsTest {

	@Configuration
	static class SettingsTestContextConfiguration {
	@Bean
	public Settings Settings() {
		return new Settings();
		}
	}
	
	@Test
	public void settingsTest1() {
		Settings s1 = new Settings();
		assertNotNull(s1);
	}
	
	@Test 
	public void settingsTest2() {
		Settings s1 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2, "Database");
//		assertTrue(s1.getId() == 3);
		assertEquals(3, s1.getId().intValue());
	}
	
	@Test
	public void getSetIdTest() {
		Settings s1 = new Settings();
		s1.setId(8);
//		assertTrue(s1.getId() == 8);
        assertEquals(8, s1.getId().intValue());
	}
	
	@Test
	public void getSetAliasTest() {
		Settings s1 = new Settings();
		s1.setAlias("someName");
//		assertTrue(s1.getAlias().equals("someName"));
        assertEquals("someName", s1.getAlias());
	}
	
	@Test
	public void getSetTrainersPerPageTest() {
		Settings s1 = new Settings();
		s1.setTrainersPerPage(2);
//		assertTrue(s1.getTrainersPerPage() == 2);
        assertEquals(2, s1.getTrainersPerPage().intValue());
	}

	
	@Test
	public void getSetBatchLength() {
		Settings s1 = new Settings();
		s1.setBatchLength(29);
//		assertTrue(s1.getBatchLength() == 29);
        assertEquals(29, s1.getBatchLength().intValue());
	}
	
	@Test
	public void getSetReportIncomingCandidatesTest() {
		Settings s1 = new Settings();
		s1.setReportIncomingCandidates(12);
//		assertTrue(s1.getReportIncomingGrads() == 12);
        assertEquals(12, s1.getReportIncomingCandidates().intValue());
	}

	@Test
	public void getSetReportOutgoingGradsTest() {
		Settings s1 = new Settings();
		s1.setReportOutgoingGrads(12);
//		assertTrue(s1.getReportIncomingGrads() == 12);
		assertEquals(12, s1.getReportOutgoingGrads().intValue());
	}
	
	@Test
	public void getSetMinBatchSizeTest() {
		Settings s1 = new Settings();
		s1.setMinBatchSize(3);
//		assertTrue(s1.getMinBatchSize() == 3);
        assertEquals(3, s1.getMinBatchSize().intValue());
	}
	
	@Test
	public void getSetMaxBatchSizeTest() {
		Settings s1 = new Settings();
		s1.setMaxBatchSize(17);
//		assertTrue(s1.getMaxBatchSize() == 17);
        assertEquals(17, s1.getMaxBatchSize().intValue());
	}
	
	@Test
	public void getSetTrainerBreakDaysTest() {
		Settings s1 = new Settings();
		s1.setTrainerBreakDays(14);
//		assertTrue(s1.getTrainerBreakDays() == 14);
        assertEquals(14, s1.getTrainerBreakDays().intValue());
	}
	
	@Test
	public void getSetDefaultLocationTest() {
		Settings s1 = new Settings();
		s1.setDefaultLocation(9);
//		assertTrue(s1.getDefaultLocation() == 9);
        assertEquals(9, s1.getDefaultLocation().intValue());
	}
	
	@Test
	public void getSetDefaultBuildingTest() {
		Settings s1 = new Settings();
		s1.setDefaultBuilding(18);
//		assertTrue(s1.getDefaultBuilding() == 18);
        assertEquals(18, s1.getDefaultBuilding().intValue());
	}
	
	@Test
	public void getSetDefaultNamePatternTest() {
		Settings s1 = new Settings();
		s1.setDefaultNamePattern("pattern");
//		assertTrue(s1.getDefaultNamePattern().equals("pattern"));
        assertEquals("pattern", s1.getDefaultNamePattern());
	}

}
