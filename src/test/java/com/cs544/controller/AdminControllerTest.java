package com.cs544.controller;

import com.cs544.domain.Course;
import com.cs544.service.CourseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class AdminControllerTest {

@InjectMocks
AdminController adminController;
@Mock
    CourseServiceImpl courseService;
Course courses;
final String courseID="cs567";

    @BeforeEach
    public void startup() throws Exception{
        MockitoAnnotations.initMocks(this);
        this.courses=new Course();
        courses.setCourseID(courseID);
        courses.setDescription("ea course");


    }
    @Test
    void getCourseById() {

        when(courseService.getCourseById(anyString())).thenReturn(courses);
        Course course= adminController.getCourseById(courseID);
        assertEquals(courseID,course.getCourseID());
        assertNotNull(course);
        assertEquals(courses.getDescription(),course.getDescription());
    }
}














































































































































































































































































































































































































































































































































































































































































