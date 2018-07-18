package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Integer>{

}
