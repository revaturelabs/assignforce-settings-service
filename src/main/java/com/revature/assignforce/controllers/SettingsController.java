package com.revature.assignforce.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.service.SettingsService;

@CrossOrigin
@RestController
public class SettingsController {

	@Autowired
	SettingsService settingService;

	// findOne
	@GetMapping(value = "{id}")
	public ResponseEntity<Settings> getById(@PathVariable int id) {
		Optional<Settings> s = settingService.findById(id);
		if (!s.isPresent())
			return new ResponseEntity<Settings>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Settings>(s.get(), HttpStatus.OK);
	}

	// update
	@PutMapping
	public ResponseEntity<Settings> update(@RequestBody Settings s) {
		s = settingService.update(s);
		if (s == null)
			return new ResponseEntity<Settings>(HttpStatus.BAD_REQUEST);
		return new ResponseEntity<Settings>(s, HttpStatus.CREATED);
	}

}
