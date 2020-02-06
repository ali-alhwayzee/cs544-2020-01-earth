package com.cs544.controller;

import com.cs544.domain.Location;
import com.cs544.domain.Session;
import com.cs544.domain.Student;
import com.cs544.domain.Timeslot;
import com.cs544.service.CourseofferingService;
import com.cs544.service.SessionService;
import com.cs544.service.TimeSlotService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/faculty")
public class FacultyController {
	
	@Autowired
	SessionService sessionService;
	
	@Autowired
	private TimeSlotService timeSlotService;
	
    @Autowired
    CourseofferingService courseofferingService;   
    
    @RequestMapping(value = "/session_students" , method = RequestMethod.GET)
    public List<Student> viewAttendanceBySession(String sessionID) {  		
    	List<Student> students = sessionService.getStudentsbySession(sessionID);
    	return students;
    }
    @RequestMapping(value = "/sessions" , method = RequestMethod.GET)
    public List<Session> getSessions() {  		
    	List<Session> sessions = sessionService.getSessions();
    	return sessions;
    }
    
    @RequestMapping(value = "/courseOffering", method = RequestMethod.GET)
    public String courseOfferingAttendances(String courseOfferingId){
    	System.out.println("Entering the FacultyController ...");
        String percentage =  courseofferingService.getCourseOfferingAttendances(courseOfferingId);
        return percentage;
    }
    
    @RequestMapping(value="/timeslots", method = RequestMethod.GET)
    public List<Timeslot> findAllTimeslot(){
    	return timeSlotService.findAll();
    }
}