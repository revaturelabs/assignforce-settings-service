package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.repos.SettingsRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Transactional
@Service
@EnableSwagger2
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository batchRepository;
	
	@Override
	public List<Settings> getAll() { // find all in table based on bean Settings
		return batchRepository.findAll();
	}

	@Override
	public Optional<Settings> findById(int id) { // find by id
		return batchRepository.findById(id);
	}

	@Override
	public Settings update(Settings b) { // update batch
		return batchRepository.save(b);
	}

	@Override
	public Settings create(Settings b) { //create batch
		return batchRepository.save(b);
	}

	@Override
	public void delete(int id) { // delete by id
		batchRepository.deleteById(id);
	}

}
