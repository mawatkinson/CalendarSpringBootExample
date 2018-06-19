package com.mwatkinson.calendar.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mwatkinson.calendar.entity.CalendarEvent;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// 

/**
 * Calendar event JPA repository
 * Provides basic CRUD operations (Create, Read, Update, Delete)
 * Additional JPA query methods for retrieving specific event collections
 * 
 * @author Mark Watkinson
 *
 */
public interface CalendarEventRepository extends CrudRepository<CalendarEvent, Long> {
	
	/**
	 * JPA Query method to fetch events for a specified calendar and day
	 * 
	 * @param name calendar title
	 * @param username the username of the calendar owner
	 * @param day the day to retieve events for
	 * @return List of matching CalendarEvent
	 */
	@Query("select ce from CalendarEvent ce where ce.calendar.name = ?1 and ce.calendar.username = ?2 and date(ce.date) = date(?3)")
	public List<CalendarEvent> findEventsForDay(String name, String username, Date day);

	/**
	 * JPA Query method to fetch events for a specified calendar and week
	 * 
	 * @param name calendar title
	 * @param username the username of the calendar owner
	 * @param day any day of the week to reteive events for
	 * @return List of matching CalendarEvent
	 */
	@Query("select ce from CalendarEvent ce where ce.calendar.name = ?1 and ce.calendar.username = ?2 and year(ce.date) = year(?3) and weekofyear(ce.date) = weekofyear(?3)")
	public List<CalendarEvent> findEventsForWeek(String name, String username, Date dayOfWeek);

	/**
	 * JPA Query method to fetch events for a specified calendar and month
	 * 
	 * @param name calendar title
	 * @param username the username of the calendar owner
	 * @param day any day of the month to reteive events for
	 * @return List of matching CalendarEvent
	 */
	@Query("select ce from CalendarEvent ce where ce.calendar.name = ?1 and ce.calendar.username = ?2 and year(ce.date) = year(?3) and month(ce.date) = month(?3)")
	public List<CalendarEvent> findEventsForMonth(String name, String username, Date dayOfMonth);

	/**
	 * JPA Query method to fetch events which are due for a reminder to be sent to the user
	 * Returns all events who's reminder date and time matches the current date and time to the minute.
	 * 
	 * @return List of matching CalendarEvent
	 */
	@Query("select ce from CalendarEvent ce where year(ce.reminder) = year(now()) and month(ce.reminder) = month(now()) and day(ce.reminder) = day(now())"
			+ " and hour(ce.reminder) = hour(now()) and minute(ce.reminder) = minute(now())")
	public List<CalendarEvent> getEventsDueForReminder();

}
