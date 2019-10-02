package com.revature.assignforce.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.revature.assignforce.beans.Settings;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Repository // create repository
@EnableSwagger2
public interface SettingsRepository extends JpaRepository<Settings, Integer>{

}
