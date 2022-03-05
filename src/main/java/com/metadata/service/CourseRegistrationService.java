package com.metadata.service;

import com.metadata.exception.CourseExceedLimitException;
import com.metadata.exception.RecordAlreadyExist;
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
        var student = studentRepository.
                    findById(parameter.getStudentId())
                    .map(this::verifyLimitCoursesPerStudent);

        var course = courseRepository.
                 findById(parameter.getCourseId())
                .map(this::verifyLimitStudentInCoure);

        var courseRegistration = mapper.courseRegistrationParameterToCourseRegistration(student.get(), course.get());

        courseRegistrationRepository.findByStudentAndCourse(student.get(), course.get())
                    .ifPresent(seller -> { throw new RecordAlreadyExist("Already exist a register with this values");});

        courseRegistrationRepository.save(courseRegistration);

        return Optional.of(String.valueOf(courseRegistrationRepository.save(courseRegistration).getId()));
    }


    private Student verifyLimitCoursesPerStudent(Student student){
        var quantity = courseRegistrationRepository.countByStudentId(student.getId());

        if  (!(quantity < 5)) {
            throw new CourseExceedLimitException("Course exceed the limit maximum of students");
        }

        return student;
    }

    private Course verifyLimitStudentInCoure(Course course){
        var quantity = courseRegistrationRepository.countByCourse(course);

        if  (!(quantity < 5)) {
            throw new StudentsExceedLimitException("Course exceed the limit maximum of students");
        }

        return course;
    }
}
