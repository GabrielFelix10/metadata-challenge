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

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
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
        var students = new ArrayList<Student>();
        var courseRegistration= courseRepository.findById(courseId).map(c -> courseRegistrationRepository.findAllByCourse(c).get());

        if( courseRegistration.isEmpty()){
            Optional.empty();
        }

        for (CourseRegistration registraton : courseRegistration.get()) {
            students.add(studentRepository.findById(registraton.getStudent().getId()).get());
        }

        return Optional.of(students);
    }

    public Optional<List<Course>> findAllCourseByStudent(long studentId) {
        var courses = new ArrayList<Course>();
        var student= studentRepository.findById(studentId);

        var courseRegistration = courseRegistrationRepository.findAllByStudent(student.get());

        for (CourseRegistration registraton : courseRegistration.get()) {
            courses.add(courseRepository.findById(registraton.getCourse().getId()).get());
        }

        return Optional.of(courses);
    }
}
