package com.mwatkinson.calendar.entity;



import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;

/**
 * Calendar JSON/JPA entity
 * 
 * @author Mark Watkinson
 *
 */
@Entity 
public class Calendar {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String name;

    private String username;
    
    public Calendar() {
		super();
	}
    
    public Calendar(String name, String username) {
		super();
		this.name = name;
		this.username = username;
	}
    
    @OneToMany(mappedBy="calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    @MapKey(name="title")
    private Map<String, CalendarEvent> events;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsernam(String username) {
		this.username = username;
	}

	public Map<String, CalendarEvent> getEvents() {
		return events;
	}

	public void setEvents(Map<String, CalendarEvent> events) {
		this.events = events;
	}


}
