package com.revature.tests;

import static org.junit.Assert.*;

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

import com.revature.assignforce.SettingsServiceApplication;
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
	private SettingsRepository settingsRepository;
	@Autowired
	private SettingsController settingsController;
	
	/**
	 * Tests if getting settings by Id returns value as expected and returns an HttpStatus OK.
	 */
	@Test
	public void getByIdTestOK() {
		Settings s1 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		Optional<Settings> op1 = Optional.of(s1);
		Mockito.when(settingsRepository.findById(3)).thenReturn(op1);
		ResponseEntity<Settings> reTest = settingsController.getById(3);
//		assertTrue(reTest.getBody().getId() == 3 && reTest.getStatusCode() == HttpStatus.OK);
		assertEquals(s1.getId().intValue(), reTest.getBody().getId().intValue());
		assertEquals(HttpStatus.OK, reTest.getStatusCode());
	}
	
	/**
	 * Tests if getting settings by a non-existent Id returns an HttpStatus NOT_FOUND as expected.
	 */
	@Test
	public void getByIdTestNotFound() {
		ResponseEntity<Settings> reTest = settingsController.getById(9);
//		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
		assertEquals(HttpStatus.NOT_FOUND, reTest.getStatusCode());
	}
	
	/**
	 * Tests if creating alias within settings works properly and returns an HttpStatus CREATED as expected.
	 */
	@Test
	public void updateTestCreateAlias() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setAlias("Java");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
//		assertTrue(reTest.getBody().getAlias().equals("Java") && reTest.getStatusCode() == HttpStatus.CREATED);
		assertEquals(s1.getAlias(), reTest.getBody().getAlias());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating alias with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestAlias() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setAlias("C++");
		ResponseEntity<Settings> reTest = settingsController.update(s1);
//		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if updating trainers per page works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateTPPage(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setTrainersPerPage(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getTrainersPerPage().intValue(), reTest.getBody().getTrainersPerPage().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating trainers per page with bad request returns and HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestTPPage() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setTrainersPerPage(-4);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating report on outgoing graduates works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateRepGrads(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setReportOutgoingGrads(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getReportOutgoingGrads().intValue(), reTest.getBody().getReportOutgoingGrads().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating report on outgoing graduates with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestRepGrads() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setReportOutgoingGrads(-4);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating batch length works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateBatchLength(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setBatchLength(48);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getBatchLength().intValue(), reTest.getBody().getBatchLength().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating batch length with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestBatchLength() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(48);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating report on incoming candidates works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateReportIG(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setReportIncomingCandidates(34);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getReportIncomingCandidates().intValue(), reTest.getBody().getReportIncomingCandidates().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating report on incoming candidates with a bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestReportIG() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(34);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating a minimum batch size works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateMinBS(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setMinBatchSize(12);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getMinBatchSize().intValue(), reTest.getBody().getMinBatchSize().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating minimum batch size with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestMinBS() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(12);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating a maximum batch size works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateMaxBS(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setMaxBatchSize(18);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getMaxBatchSize().intValue(), reTest.getBody().getMaxBatchSize().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}
	
	/**
	 * Tests if updating maximum batch size with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestMaxBS() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(18);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating trainer break days works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateTrainBD(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setTrainerBreakDays(5);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getTrainerBreakDays().intValue(), reTest.getBody().getTrainerBreakDays().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating trainer break days with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestTrainBD() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(5);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating default location works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateDefLoc(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setDefaultLocation(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getDefaultLocation().intValue(), reTest.getBody().getDefaultLocation().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating default location with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestDefLoc() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	/**
	 * Tests if creating default building works as expected and returns an HttpStatus CREATED.
	 */
	@Test
	public void updateTestCreateDefBld(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setDefaultBuilding(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getDefaultBuilding().intValue(), reTest.getBody().getDefaultBuilding().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	/**
	 * Tests if updating default building with bad request returns an HttpStatus BAD_REQUEST as expected.
	 */
	@Test
	public void updateTestBadRequestDefBld() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}
	
}
