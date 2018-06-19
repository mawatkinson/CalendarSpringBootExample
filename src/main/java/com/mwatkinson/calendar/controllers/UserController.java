package com.mwatkinson.calendar.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mwatkinson.calendar.entity.User;
import com.mwatkinson.calendar.repository.UserRepository;

/**
 * Calendar application REST services for user operations
 * 
 * @author Mark Watkinson
 *
 */
@Controller    // This means that this class is a Controller
@RequestMapping(path="/user") // This means URL's start with /demo (after Application path)
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * REST POST mapping to enrol a new user in the calendar application
	 * 
	 * @param username String username for the new user
	 * @param password String password for the new user
	 */
	@PostMapping(path="/sign-up", params= { "username", "password" })
    public @ResponseBody String signUp(@RequestParam String username, @RequestParam String password) {
		String response = "";
		if (userRepository.findByUsername(username)==null) {
			User user = new User(username, password);
			userRepository.save(user);
			response = "User created.";
		} else {
			response = "Username already exists.";
		}
		log.info("Sign up called for username: " + username + " -> " + response);
		return response;
    }
	
	/**
	 * REST GET mapping to authenticate the a user
	 * If the username and password are valid, returns an auth token to be used with various calendar endpoints to provide security 
	 * 
	 * @param username username for the user to authenticate
	 * @param password password of the user to authenticate
	 * @return String Auth token or message indicating the username or password were incorrect
	 */
	@GetMapping(path="/authenticate", params= { "username", "password" })
	public @ResponseBody String authenticate(@RequestParam String username, @RequestParam String password) {
		String response = "";
		String successString = "";
		User user = userRepository.findByUsername(username);
		if (user != null && user.validatePassword(password)) {
			response = user.authenticateUser();
			userRepository.save(user);
			successString = "Success";
		} else {
			response = "Invalid Username or Password";
			successString = new String(response);
		}
		log.info("Authenticate called for username: " + username + " -> " + successString);
		return response;
	}
}
