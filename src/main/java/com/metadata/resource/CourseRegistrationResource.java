package com.metadata.resource;

import com.metadata.exception.RecordAlreadyExist;
import com.metadata.resource.parameters.CourseRegistrationParameter;
import com.metadata.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CourseRegistrationResource {

    @Autowired
    private CourseRegistrationService service;

    @ExceptionHandler(RecordAlreadyExist.class)
    @PostMapping("/course/registration")
    public ResponseEntity<String> persistPartner(@Valid @RequestBody final CourseRegistrationParameter parameter) {

        return service.associateStudentToCourse(parameter)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}

