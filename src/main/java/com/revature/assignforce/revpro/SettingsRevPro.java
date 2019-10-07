package com.revature.assignforce.revpro;

import com.revature.assignforce.beans.Settings;
import java.util.List;
import java.util.Optional;

/**
 * @author Ella Rozenblum
 *
 * This Class is a prepared skeleton (no functionality) in anticipation for the integration of AssignForce into Revature Pro. 
 * The bellow methods are guidelines to help you identify the intended functionality we had in mind. Your batch may have a
 * shift in purpose by the time this project falls into your hands.
 *
 * The purpose of this class is to house methods for either retrieving and/or modifying information from the Revature Pro API.
 * Each method needs to handle both the communication with the Revature Pro API and the conversion of the data to 
 * AssignForce.
 *
 * Keep in mind that it is important that the information originating from RevPro needs to be identifiable as such and not
 * confused with information that is originating from within AssignForce. 
 */
public class SettingsRevPro {
	
	/**
	 * The purpose of this method is to return a list of all skills. It should send a GET request and convert the response 
	 * to a Skill list, preferably using Jackson or GSON. 
	 * 
	 * @return: this method returns a list containing all of the elements in the SKill list.
	 */	
	public List<Settings> getAll() {
		List<Settings> allSettings = new List<Settings>();// creating new Settings List
		//Send an HTTP request to endpoint to grab all settings
		//assigning HTTP Response to allSettings List
		//return List of allSettings
		return null;
	}

	/**
	 * The purpose of this method is to return an optional value that corresponds to a specific setting. It should send a GET request
	 * and convert the response to a Setting object, preferably using Jackson or GSON. You should take advantage of the Optional type 
	 * to check if the setting is actually found. The isPresent() method will tell you if the object is null or not and the get() 
	 * method will actually return the value.
	 *  	 
	 * @param id: this parameter is the id corresponding to the specific setting that needs to be retrieved.
	 * @return: this method returns an Optional value depending on whether the specific setting was found. 
	 */
	public Optional<Settings> findById(int id) {
		Object Setting;
		//send an HTTP request to endpoint for Setting by ID
		//set object of Setting to the HTTP response
		//return Setting
		return null;
	}

	
	/**
	 * The purpose of this method is to update and save a new Settings object/record. It should send a PUT request 
	 * with the updated record to the Revature Pro database.
	 * 
	 * @param b: this parameter is the Setting object/record that needs to be updated and saved.
	 * @return: this method returns an updated Settings object/record.
	 */
	public Settings update(Settings b) {
		//take current Settings record 
		//send updated record to RevPro database
		return null;
	}

	
	/**
	 * The purpose of this method is to create and save a new Settings object. It should send a POST request 
	 * to insert the new Settings object into the Revature Pro database. 
	 * 
	 * @param b: this parameter is the Setting object/record that needs to be created and saved.
	 * @return: this method returns a newly created Settings object. 
	 */
	public Settings create(Settings b) {
		//save a new Setting
		//return the new Setting object into the endpoint to insert new Setting into RevPro database
		return null;
	}

	
	/**
	 * The purpose of this method is to delete a Settings object/record by ID. It should send a DELETE request 
	 * to delete the Settings object from the Revature Pro database. 
	 * 
	 * @param id: this parameter is the id corresponding to the specific setting that needs to be deleted.
	 */	
	public void delete(int id) {
		//delete from RevPro database
	}

}