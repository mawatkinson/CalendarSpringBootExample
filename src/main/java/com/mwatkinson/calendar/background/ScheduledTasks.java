package com.mwatkinson.calendar.background;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.mwatkinson.calendar.entity.CalendarEvent;
import com.mwatkinson.calendar.repository.CalendarEventRepository;

/**
 * ScheduledTasks background tasks for calendar application
 * 
 * @author Mark Watkinson
 *
 */
@Component
public class ScheduledTasks {

    private static final Logger log = LoggerFactory.getLogger(ScheduledTasks.class);
	
	@Autowired
	private CalendarEventRepository calendarEventRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * Cron to send out event reminders. Current implementation just logs reminders to the console.
     * Configured to run once per minute
     */
    @Scheduled(cron="0 */1 * * * *")
    public void sendReminders() {
    	log.info("Checking for events to send a reminder at {}", dateFormat.format(new Date()));
    	for (CalendarEvent remindEvent : calendarEventRepository.getEventsDueForReminder()) {
    		remindEvent.setReminder_sent(true);
    		calendarEventRepository.save(remindEvent);
    		log.info("Sending reminder for event : {}", remindEvent.getReminderString());
    	}
    }
}