package com.metadata.service;

import com.metadata.resource.parameters.CourseRegistrationParameter;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CourseRegistrationService {
    public Optional<String> associateStudentToCourse(CourseRegistrationParameter parameter) {
        return Optional.of("associated");
    }
}
