package com.metadata.model;


import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String name;

    @OneToMany(mappedBy = "course")
    Set<CourseRegistration> registrations;
}
