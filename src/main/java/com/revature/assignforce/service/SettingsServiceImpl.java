package com.revature.assignforce.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.assignforce.beans.Settings;
import com.revature.assignforce.repos.SettingsRepository;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * 
 * DAOIml with list of methods:
 *  - get all batches
 *  - get batch by id (optional)
 *  - create batch 
 *  - update batch
 *  - delete batch by id 
 *
 */
@Transactional
@Service
@EnableSwagger2
public class SettingsServiceImpl implements SettingsService {

	@Autowired
	private SettingsRepository batchRepository;
	
	/*
	 * @return all batches as list
	 */
	@Override
	public List<Settings> getAll() { // find all in table based on bean Settings
		return batchRepository.findAll();
	}

	/*
	 * @return batch by id
	 */
	@Override
	public Optional<Settings> findById(int id) { // find by id
		return batchRepository.findById(id);
	}

	/*	 
	 * @return updated batch
	 * 
	 */
	@Override
	public Settings update(Settings b) { // update batch
		return batchRepository.save(b);
	}

	/*	 
	 * @return created batch
	 * 
	 */
	@Override
	public Settings create(Settings b) { //create batch
		return batchRepository.save(b);
	}

	/*
	 * @see batch by id and delete it
	 */
	@Override
	public void delete(int id) { // delete by id
		batchRepository.deleteById(id);
	}

}
