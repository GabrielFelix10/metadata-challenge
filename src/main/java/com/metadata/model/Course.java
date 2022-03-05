package com.metadata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;


@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="course_sequence")
    @SequenceGenerator(name="course_sequence", sequenceName="course_seq")
    @JsonIgnore
    private long id;

    @JsonProperty
    @NotNull(message = "Name can't be null or invalid")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations;

    public Course(long id, String name, Set<CourseRegistration> registrations) {
        this.id = id;
        this.name = name;
        this.registrations = registrations;
    }


    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<CourseRegistration> getRegistrations() {
        return registrations;
    }
}
