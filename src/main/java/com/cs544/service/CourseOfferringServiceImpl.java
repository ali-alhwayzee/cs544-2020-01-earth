package com.cs544.service;

import com.cs544.dao.CourseOfferingRepository;
import com.cs544.dao.CourseRepository;
import com.cs544.dao.RegisterRepository;
import com.cs544.domain.Course;
import com.cs544.domain.CourseOffering;
import com.cs544.domain.Register;
import com.cs544.domain.Session;
import com.cs544.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseOfferringServiceImpl implements  CourseofferingService {

    @Autowired
    CourseOfferingRepository courseOfferingRepository;
    
    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    CourseRepository courseRepository;

    @Override
    public CourseOffering add(@Valid CourseOffering courseOffering, String id) {

        Course course=  courseRepository.getCourseByCourseID(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));


            courseOffering.setCourse(course);

         return courseOfferingRepository.save(courseOffering);
    }

    @Override
    public ResponseEntity<?> deleteCourseOfferingById(String id) {
        CourseOffering course=  courseOfferingRepository.getCourseOfferingByCourseOfferingID(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));

        courseOfferingRepository.delete(course);

        return ResponseEntity.ok().build();
    }


    @Override
    public CourseOffering update(String id,@Valid CourseOffering courseOffering) {

        CourseOffering courses=  courseOfferingRepository.getCourseOfferingByCourseOfferingID(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));
        courses.setCourse(courseOffering.getCourse());
        courses.setEndDate(courseOffering.getEndDate());
        courses.setStartDate(courseOffering.getStartDate());
        courses.setSession(courseOffering.getSession());

        CourseOffering courseupdate=  courseOfferingRepository.save(courses);
        return courseupdate;
    }

    @Override
    public CourseOffering getCourseOfferingById(String id) {
        return courseOfferingRepository.getCourseOfferingByCourseOfferingID(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));

    }
    
	@Override
	public String getCourseOfferingAttendances(String courseOfferingId) {
//    	List<Student> students = new ArrayList<Student>();
    	System.out.println("Entering the CourseOfferingServiceImpl ...");
		
    	List<Session> listSession;
    	int courseAttendances = 0;
    	long registeredStudents = 0;
    	int courseSessions = 0;
    	
    	//1. Count number of sessions of the offering course
    	//Optional<OfferedCourse> courseOffering = courseOfferingDao.findById(Long.valueOf(courseOfferingId));
    	Optional<CourseOffering> courseOffering = courseOfferingRepository.getOfferedCourseByCourseOfferingID(courseOfferingId);
    	
    	if(courseOffering.isPresent()) {
    		listSession = courseOffering.get().getSession();
    		courseSessions = listSession.size();
    		for(Session ses : listSession) {
    			courseAttendances += ses.getRecords().size();
    		}
    	}
    	else {
    		return "0.0%";
    	}
    	
    	
    	//2. How to get list of registered students
//    	List<Optional<Register>> registers = getRegisteredStudents(courseOfferingId);
    	Iterable<Register> listRegister = registerRepository.findAll();
    	
    	List<Register> registers = new ArrayList<>();
    	listRegister.iterator().forEachRemaining(registers::add);
    	
    	registeredStudents = registers.stream().filter(s->s.getOfferedCourses().getCourseOfferingID().equals(courseOfferingId)).count();
    	
    	if(registeredStudents != 0) {
    		return "Number of attendances / All course offering sessions: " + courseAttendances + "/" + courseSessions*registeredStudents;
//    		return convertToPercentage(Math.round(courseAttendances/(courseSessions*registeredStudents)));
    	}
    	
        return "Number of attendances / All course offering sessions: 0.0%";
	}

	@Override
	public List<Optional<Register>> getRegisteredStudents(String courseOfferingId) {
		
		CourseOffering offeredCourse = new CourseOffering();
		offeredCourse.setCourseOfferingID(courseOfferingId);
		List<Optional<Register>> listRegister = registerRepository.findByCourseOffering(offeredCourse);
		
		return listRegister;
	}
	
	/*private String convertToPercentage(double dNum) {
	    DecimalFormat percentFormat = new DecimalFormat("0.0%");
	    percentFormat.setDecimalSeparatorAlwaysShown(false);
	    percentFormat.setMinimumFractionDigits(1);
	    percentFormat.setMaximumFractionDigits(2);
	    return percentFormat.format(dNum);
	}*/

    /*@Override
    public CourseOffering getCourseOfferingByCourseId(String id) {
        return  courseOfferingRepository.getCourseOfferingByCourseId(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id",id));
    }*/
}
