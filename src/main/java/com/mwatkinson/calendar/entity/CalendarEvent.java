package com.mwatkinson.calendar.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Calendar Event JSON/JPA entity
 * 
 * @author Mark Watkinson
 *
 */
@Entity
public class CalendarEvent {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;
    
    @ManyToOne
    @JoinColumn(name = "calendar_id")
    @JsonIgnore
    private Calendar calendar;
    
    private String title;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    
    private String location;
    
    private String attendees;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date reminder;
    
    private boolean reminder_sent;

	public CalendarEvent() {
		super();
	}

	public CalendarEvent(Calendar calendar, String title, Date date_time, String location, String attendees,
			Date reminder, boolean reminder_sent) {
		super();
		this.calendar = calendar;
		this.title = title;
		this.date = date_time;
		this.location = location;
		this.attendees = attendees;
		this.reminder = reminder;
		this.reminder_sent = reminder_sent;
	}
	
	public void update(Date date, String location, String attendees,
			Date reminder) {
		this.date = date;
		this.location = location;
		this.attendees = attendees;
		this.reminder = reminder;
		
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Calendar getCalendar() {
		return calendar;
	}

	public void setCalendar_id(Calendar calendar) {
		this.calendar = calendar;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getDate_time() {
		return date;
	}

	public void setDate_time(Date date) {
		this.date = date;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAttendees() {
		return attendees;
	}

	public void setAttendees(String attendees) {
		this.attendees = attendees;
	}

	public Date getReminder_date_time() {
		return reminder;
	}

	public void setReminder_date_time(Date reminder) {
		this.reminder = reminder;
	}

	public boolean isReminder_sent() {
		return reminder_sent;
	}

	public void setReminder_sent(boolean reminder_sent) {
		this.reminder_sent = reminder_sent;
	}
    
    public String getReminderString() {
    	return "Calendar: " + calendar.getName() + 
    			" Username: " + calendar.getUsername() + 
    			" Event Title: " + title + 
    			" Event Date: " + date + 
    			" Event Location " + location + 
    			" Attendees " + attendees;
    }

}
