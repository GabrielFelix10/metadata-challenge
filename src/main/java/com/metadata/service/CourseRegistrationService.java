package com.metadata.service;

import com.metadata.repository.CourseRegistrationRepository;
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
    private CourseRegistrationRepository repository;

    public Optional<String> associateStudentToCourse(CourseRegistrationParameter parameter) {

        var courseRegistration = mapper.courseRegistrationParameterToCourseRegistration(parameter);

        return Optional.of(String.valueOf(repository.save(courseRegistration).getId()));
    }
}
