package com.mwatkinson.calendar.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Calendar JSON/JPA entity
 * Provides password hashing to protect client user password - no plaintext passwords in the database
 * 
 * @author Mark Watkinson
 *
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String passwordHash;
    private String authToken;
    private Date authTimestamp;
    
    public User() {
    	super();
    }
    
    public User(String username, String password) {
    	super();
    	this.username = username;
    	this.setPassword(password);
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }
    
    public boolean validatePassword(String password) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
    	return encoder.matches(password, passwordHash);
    }

    public void setPassword(String password) {
    	PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.passwordHash = encoder.encode(password);
    }
    
    public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Date getAuthTimestamp() {
		return authTimestamp;
	}

	public void setAuthTimestamp(Date authTimestamp) {
		this.authTimestamp = authTimestamp;
	}

	public String authenticateUser() {
    	authToken = UUID.randomUUID().toString();
    	authTimestamp = new Date();
    	
    	return authToken;
    }
}
