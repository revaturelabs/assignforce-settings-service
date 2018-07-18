package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.repos.SettingsRepository;

@Transactional
@Service
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository batchRepository;
	
	@Override
	public List<Settings> getAll() {
		return batchRepository.findAll();
	}

	@Override
	public Optional<Settings> findById(int id) {
		return batchRepository.findById(id);
	}

	@Override
	public Settings update(Settings b) {
		return batchRepository.save(b);
	}

	@Override
	public Settings create(Settings b) {
		return batchRepository.save(b);
	}

	@Override
	public void delete(int id) {
		batchRepository.deleteById(id);
	}

}
