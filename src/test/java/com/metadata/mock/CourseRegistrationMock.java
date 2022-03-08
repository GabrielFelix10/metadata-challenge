package com.metadata.mock;

import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;

public class CourseRegistrationMock {

    public static CourseRegistration mock(Student student, Course course) {
        return buildCourseRegistrationMock(student, course);
    }

    private static CourseRegistration buildCourseRegistrationMock(Student student, Course course){
        return new CourseRegistration(1L, student, course);
    }
}
