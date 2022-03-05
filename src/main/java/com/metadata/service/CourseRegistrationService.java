package com.metadata.service;

import com.metadata.exception.CourseExceedLimitException;
import com.metadata.exception.StudentsExceedLimitException;
import com.metadata.model.Course;
import com.metadata.model.Student;
import com.metadata.repository.CourseRegistrationRepository;
import com.metadata.repository.CourseRepository;
import com.metadata.repository.StudentRepository;
import com.metadata.resource.parameters.CourseRegistrationParameter;
import com.metadata.service.mapper.CourseRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseRegistrationService {

    @Autowired
    private CourseRegistrationMapper mapper;

    @Autowired
    private CourseRegistrationRepository courseRegistrationRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    public Optional<String> associateStudentToCourse(CourseRegistrationParameter parameter) {
        studentRepository.
                findById(parameter.getStudentId())
                .ifPresent(this::verifyLimitCoursesPerStudent);

        courseRepository.
                findById(parameter.getCourseId())
                .ifPresent(this::verifyLimitStudentInCoure);

        var courseRegistration = mapper.courseRegistrationParameterToCourseRegistration(parameter);

        return Optional.of(String.valueOf(courseRegistrationRepository.save(courseRegistration).getId()));
    }


    private void verifyLimitCoursesPerStudent(Student student){
        var quantity = courseRegistrationRepository.countByStudent(student);

        if  (!(quantity < 5)) {
            throw new CourseExceedLimitException("Course exceed the limit maximum of students");
        }
    }

    private void verifyLimitStudentInCoure(Course course){
        var quantity = courseRegistrationRepository.countByCourse(course);

        if  (!(quantity < 5)) {
            throw new StudentsExceedLimitException("Course exceed the limit maximum of students");
        }

    }
}
