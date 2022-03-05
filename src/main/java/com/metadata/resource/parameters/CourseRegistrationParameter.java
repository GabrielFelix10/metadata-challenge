package com.metadata.resource.parameters;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class CourseRegistrationParameter {

    @NotNull(message = "Nome nao pode ser nulo ou invalido")
    @JsonProperty
    private Long studentId;

    @NotNull(message = "Nome do dono nao pode ser nulo ou invalido")
    @JsonProperty
    private Long courseId;

    public CourseRegistrationParameter(@NotNull(message = "Nome nao pode ser nulo ou invalido") Long studentId, @NotNull(message = "Nome do dono nao pode ser nulo ou invalido") Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getCourseId() {
        return courseId;
    }
}
