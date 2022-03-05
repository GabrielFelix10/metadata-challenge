package com.metadata.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="student_sequence")
    @SequenceGenerator(name="student_sequence", sequenceName="student_seq")
    @JsonIgnore
    private long id;

    @JsonProperty
    @NotNull(message = "Name can't be null or invalid")
    @Column(unique=true)
    @Size(max = 20)
    private String firstName;

    @JsonIgnore
    @OneToMany(mappedBy = "student")
    Set<CourseRegistration> registrations;


    public Student(long id, String firstName, Set<CourseRegistration> registrations) {
        this.id = id;
        this.firstName = firstName;
        this.registrations = registrations;
    }

    public Student(String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public Student() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public Set<CourseRegistration> getRegistrations() {
        return registrations;
    }
}
