package com.metadata.resource.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CourseRegistrationParameter {

    @NotNull(message = "Nome nao pode ser nulo ou invalido")
    @JsonProperty
    private String studentId;

    @NotNull(message = "Nome do dono nao pode ser nulo ou invalido")
    @JsonProperty
    private String courseId;
}
