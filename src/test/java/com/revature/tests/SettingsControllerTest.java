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
	@Bean // bean (SettingsService) will be tested 
	public SettingsService SettingsService() {
		return new SettingsServiceImpl();
		}
	@Bean // bean (SettingsRepository) will be tested
	public SettingsRepository SettingsRepository() {
		return Mockito.mock(SettingsRepository.class);
		}
	@Bean // bean (SettingsController) will be tested
	public SettingsController SettingsController() {
		return new SettingsController();
		}
	}
	
	
	@Autowired
	private SettingsRepository settingsRepository;
	@Autowired
	private SettingsController settingsController;
	
	@Test // method check get by id with status OK - when id was found
	public void getByIdTestOK() {
		Settings s1 = new Settings(3, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		Optional<Settings> op1 = Optional.of(s1);
		Mockito.when(settingsRepository.findById(3)).thenReturn(op1);
		ResponseEntity<Settings> reTest = settingsController.getById(3);
//		assertTrue(reTest.getBody().getId() == 3 && reTest.getStatusCode() == HttpStatus.OK);
		assertEquals(s1.getId().intValue(), reTest.getBody().getId().intValue());
		assertEquals(HttpStatus.OK, reTest.getStatusCode());
	}
	
	@Test // method check get by id with status NOT_FOUND - when id wasn't found
	public void getByIdTestNotFound() {
		ResponseEntity<Settings> reTest = settingsController.getById(9);
//		assertTrue(reTest.getStatusCode() == HttpStatus.NOT_FOUND);
		assertEquals(HttpStatus.NOT_FOUND, reTest.getStatusCode());
	}
	
	@Test // method check that update alias "Java" was successful
	public void updateTestCreateAlias() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setAlias("Java");
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
//		assertTrue(reTest.getBody().getAlias().equals("Java") && reTest.getStatusCode() == HttpStatus.CREATED);
		assertEquals(s1.getAlias(), reTest.getBody().getAlias());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that alias wasn't update successful
	public void updateTestBadRequestAlias() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setAlias("C++");
		ResponseEntity<Settings> reTest = settingsController.update(s1);
//		assertTrue(reTest.getStatusCode() == HttpStatus.BAD_REQUEST);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creation TPPage had status created
	public void updateTestCreateTPPage(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setTrainersPerPage(2);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getTrainersPerPage().intValue(), reTest.getBody().getTrainersPerPage().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that TPPage had bad request during creation  
	public void updateTestBadRequestTPPage() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setTrainersPerPage(-4);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that ReportOutgoingGrads was created successful
	public void updateTestCreateRepGrads(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setReportOutgoingGrads(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getReportOutgoingGrads().intValue(), reTest.getBody().getReportOutgoingGrads().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during update ReportOutgoingGrads was successful or not
	public void updateTestBadRequestRepGrads() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setReportOutgoingGrads(-4);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of Batch length was successful
	public void updateTestCreateBatchLength(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setBatchLength(48);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getBatchLength().intValue(), reTest.getBody().getBatchLength().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of Batch length had some issues 
	public void updateTestBadRequestBatchLength() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(48);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of ReportIG was successful
	public void updateTestCreateReportIG(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setReportIncomingCandidates(34);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getReportIncomingCandidates().intValue(), reTest.getBody().getReportIncomingCandidates().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of reTest had some issues 
	public void updateTestBadRequestReportIG() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(34);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of minimal size of batch was successful
	public void updateTestCreateMinBS(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setMinBatchSize(12);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getMinBatchSize().intValue(), reTest.getBody().getMinBatchSize().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of minimal size of batch had some issues
	public void updateTestBadRequestMinBS() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(12);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of maximum batch size was successful
	public void updateTestCreateMaxBS(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setMaxBatchSize(18);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getMaxBatchSize().intValue(), reTest.getBody().getMaxBatchSize().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of maximum batch size had some issues
	public void updateTestBadRequestMaxBS() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(18);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of BD for trainers was successful
	public void updateTestCreateTrainBD(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setTrainerBreakDays(5);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getTrainerBreakDays().intValue(), reTest.getBody().getTrainerBreakDays().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of BD for trainers had some issues
	public void updateTestBadRequestTrainBD() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(5);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of default location was successful
	public void updateTestCreateDefLoc(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setDefaultLocation(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getDefaultLocation().intValue(), reTest.getBody().getDefaultLocation().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of default location had some issues
	public void updateTestBadRequestDefLoc() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}

	@Test // method check that creating of default building was successful
	public void updateTestCreateDefBld(){
		Settings s1 = new Settings(6, "SettingsSix", 3, 3, 50, 25, 8, 20, 10, 5, 2,  "Database");
		s1.setDefaultBuilding(1);
		Mockito.when(settingsRepository.save(s1)).thenReturn(s1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(s1.getDefaultBuilding().intValue(), reTest.getBody().getDefaultBuilding().intValue());
		assertEquals(HttpStatus.CREATED, reTest.getStatusCode());
	}

	@Test // method check that during creating of default building had some issues
	public void updateTestBadRequestDefBld() {
		Settings s1 = new Settings(5, "SettingsThree", 2, 2, 48, 34, 12, 12, 5, 6, 2,  "Database");
		s1.setBatchLength(1);
		ResponseEntity<Settings> reTest = settingsController.update(s1);
		assertEquals(HttpStatus.BAD_REQUEST, reTest.getStatusCode());
	}
	
}
