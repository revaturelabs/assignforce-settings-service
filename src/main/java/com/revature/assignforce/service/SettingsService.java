package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import com.revature.assignforce.beans.Settings;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
public interface SettingsService {
	
	// DAO with list of methods
	List<Settings> getAll(); // get all from table based on bean Settings
	Optional<Settings> findById(int id); // find by id
	Settings update(Settings b); // update batch
	Settings create(Settings b); // create batch
	void delete(int id); // delete assign id
}
