package com.cs544.domain;

import java.util.Date;

public class Session {

	private long id;
	private Location location;
	private Timeslot timeslot;
	private Date date ;
	
  
	public Session() {}
	public Session(Location location, Timeslot timeslot, Date date) {
		this.location = location;
		this.timeslot = timeslot;
		this.date = date;
	}

	
	
	public long getId() {
		return id;
	}
	private void setId(long id) {
		this.id = id;
	}
	public Location getLocation() {
		return location;
	}
	public void setLocation(Location location) {
		this.location = location;
	}
	public Timeslot getTimeslot() {
		return timeslot;
	}
	public void setTimeslot(Timeslot timeslot) {
		this.timeslot = timeslot;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}
