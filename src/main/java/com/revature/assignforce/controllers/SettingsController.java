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
import com.wordnik.swagger.annotations.Api;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@EnableSwagger2
@Api(value="Settings-data", description="Operation define some settings during of using revature.assignforce")
public class SettingsController {

	@Autowired  
	SettingsService settingService;

	// implement the URL handler to findOne by "id" in our bean settingService
	@GetMapping(value = "{id}")	// implement the URL handler 
	public ResponseEntity<Settings> getById(@PathVariable int id) {
		Optional<Settings> s = settingService.findById(id);
		if (!s.isPresent())
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(s.get(), HttpStatus.OK);
	}

	// update
	@PutMapping	// that annotation acts as a shortcut
	public ResponseEntity<Settings> update(@RequestBody Settings s) {
		s = settingService.update(s);
		if (s == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<>(s, HttpStatus.CREATED);
	}

}
