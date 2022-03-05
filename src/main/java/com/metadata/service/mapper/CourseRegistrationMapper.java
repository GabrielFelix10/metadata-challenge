package com.metadata.service.mapper;

import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;
import org.springframework.stereotype.Component;

@Component
public class CourseRegistrationMapper {

    public CourseRegistration courseRegistrationParameterToCourseRegistration(Student student, Course course) {
        return new CourseRegistration(student, course);
    }
}
