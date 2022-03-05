package com.metadata.service.mapper;

import com.metadata.model.CourseRegistration;
import com.metadata.resource.parameters.CourseRegistrationParameter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CourseRegistrationMapper {

    public CourseRegistration courseRegistrationParameterToCourseRegistration(final CourseRegistrationParameter parameter) {
        return new ModelMapper().map(parameter, CourseRegistration.class);
    }
}
