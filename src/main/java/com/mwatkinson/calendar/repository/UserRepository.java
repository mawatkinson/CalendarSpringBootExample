package com.mwatkinson.calendar.repository;

import org.springframework.data.repository.CrudRepository;

import com.mwatkinson.calendar.entity.User;

/**
 * User JPA repository
 * Provides basic CRUD operations (Create, Read, Update, Delete)
 * Additional JPA query methods for retrieving specific users
 * 
 * @author Mark Watkinson
 *
 */
public interface UserRepository extends CrudRepository<User, Long> {
	
	/**
	 * JPA Query method to return a user as specified by username
	 * 
	 * @param username the username to retrieve
	 * @return
	 */
	public User findByUsername(String username);

}
