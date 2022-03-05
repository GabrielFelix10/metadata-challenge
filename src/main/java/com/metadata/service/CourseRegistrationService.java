package com.metadata.service;

import com.metadata.exception.CourseExceedLimitException;
import com.metadata.exception.RecordAlreadyExist;
import com.metadata.exception.RecordNotExist;
import com.metadata.exception.StudentsExceedLimitException;
import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;
import com.metadata.repository.CourseRegistrationRepository;
import com.metadata.repository.CourseRepository;
import com.metadata.repository.StudentRepository;
import com.metadata.resource.parameters.CourseRegistrationParameter;
import com.metadata.service.mapper.CourseRegistrationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                    .map(this::verifyLimitCoursesPerStudent)
                    .orElseThrow(() -> {throw new RecordNotExist("Register dosen't exist with this values");});

        var course = courseRepository.
                 findById(parameter.getCourseId())
                .map(this::verifyLimitStudentInCoure)
                .orElseThrow(() -> {throw new RecordNotExist("Register dosen't exist with this values");});

        var courseRegistration = mapper.courseRegistrationParameterToCourseRegistration(student, course);

        courseRegistrationRepository.findByStudentAndCourse(student, course)
                    .ifPresent(register -> { throw new RecordAlreadyExist("Already exist a register with this values");});

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
        var quantity = courseRegistrationRepository.countByCourse(course.getId());

        if  (!(quantity < 50)) {
            throw new StudentsExceedLimitException("Course exceed the limit maximum of students");
        }

        return course;
    }

    public Optional<List<Student>> findAllStudentsByCourse(long courseId) {
        return courseRepository
                .findById(courseId)
                .flatMap(c -> courseRegistrationRepository.findAllByCourse(c))
                .map(courseRegistrations -> courseRegistrations.stream()
                        .map(courseRegistration -> studentRepository.findById(courseRegistration.getStudent().getId()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }

    public Optional<List<Course>> findAllCourseByStudent(long studentId) {
        return studentRepository.
                findById(studentId)
                .flatMap(courseRegistrationRepository::findAllByStudent)
                .map(courses -> courses.stream()
                        .map(c -> courseRepository.findById(c.getId()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .collect(Collectors.toList()));
    }
}
