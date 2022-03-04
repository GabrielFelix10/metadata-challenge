package com.metadata.resource;

import com.metadata.resource.parameters.CourseRegistrationParameter;
import com.metadata.service.CourseRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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
}

