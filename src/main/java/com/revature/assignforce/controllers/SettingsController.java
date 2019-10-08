package com.revature.assignforce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.service.SettingsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * A controller for retrieving, creating, updating and deleting settings information
 *
 */
@RestController
@EnableSwagger2
@Api(value="SettingsController")
public class SettingsController {

	@Autowired  
	SettingsService settingService;
	
	/** 
	 * Get Settings Information by Id
	 * Implements the URL handler to findOne by "id" in our bean settingService
	 * @param   id setting
	 * @return	ResponseEntity with Status OK/ NOT FOUND
	 * @see		Settings
	 */
	@GetMapping(value = "{id}")
	@ApiOperation(value = "Get Settings Information by Id", 
	response = ResponseEntity.class, 
	tags = "SettingsController", nickname= "getSettingsById")
	@ApiResponses(value = {
			@ApiResponse(code = 404, message = "Not Found"), 
            @ApiResponse(code = 200, message = "OK", response = Settings.class)}) 
	public ResponseEntity<Settings> getById(@ApiParam(name = "id") @PathVariable int id) {
		Optional<Settings> s = settingService.findById(id);
		if (!s.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(s.get(), HttpStatus.OK);
	}
	
	/** 
	 * Create New Settings Information
	 * @param 	s	Settings
	 * @return		ResponseEntity Status CREATED / BAD REQUEST 
	 * @see			Settings
	 */
	@PutMapping
	@ApiOperation(value = "Create New Settings Information", 
	response = ResponseEntity.class, 
	tags = "SettingsController", nickname= "updateSettings")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = "Bad Request"), 
            @ApiResponse(code = 201, message = "Created", response = Settings.class)})
	public ResponseEntity<Settings> update(@RequestBody Settings s) {
		s = settingService.update(s);
		if (s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

}
