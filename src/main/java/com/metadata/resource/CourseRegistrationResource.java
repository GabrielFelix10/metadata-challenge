package com.metadata.resource;

import com.metadata.model.Course;
import com.metadata.model.Student;
import com.metadata.resource.parameters.CourseRegistrationParameter;
import com.metadata.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CourseRegistrationResource {

    @Autowired
    private CourseRegistrationService service;

    @PostMapping("/course/registration")
    public ResponseEntity<String> persistPartner(@Valid @RequestBody final CourseRegistrationParameter parameter) {

        return service.associateStudentToCourse(parameter)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/course/registration/{id}")
    public ResponseEntity<List<Student>> findAllStudentsByCourse(@PathVariable("id") final long courseId) {

        return service.findAllStudentsByCourse(courseId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

    @GetMapping("/student/registration/{id}")
    public ResponseEntity<List<Course>> findAllCourseBySpecuficStudent(@PathVariable("id") final long studentId) {

        return service.findAllCourseByStudent(studentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }
}

