package com.metadata.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @JsonProperty
    private String firstName;

    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;

}
