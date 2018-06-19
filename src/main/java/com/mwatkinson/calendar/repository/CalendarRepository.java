package com.mwatkinson.calendar.repository;

import org.springframework.data.repository.CrudRepository;

import com.mwatkinson.calendar.entity.Calendar;

/**
 * Calendar JPA repository
 * Provides basic CRUD operations (Create, Read, Update, Delete)
 * Additional JPA query methods for retrieving specific calendars
 * 
 * @author Mark Watkinson
 *
 */
public interface CalendarRepository extends CrudRepository<Calendar, Long> {
	
	/**
	 * Retrieves the specified calendar
	 * 
	 * @param name calendar title
	 * @param username username
	 * @return Matching Calendar
	 */
	public Calendar findByNameAndUsername(String name, String username);

}
