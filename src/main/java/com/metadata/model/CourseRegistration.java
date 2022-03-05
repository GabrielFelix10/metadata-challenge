package com.metadata.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator="course_registration_sequence")
    @SequenceGenerator(name="course_registration_sequence", sequenceName="course_regis_seq")
    Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    Course course;

    public CourseRegistration(Long id, Student student, Course course) {
        this.id = id;
        this.student = student;
        this.course = course;
    }

    public CourseRegistration( Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public CourseRegistration() {

    }

    public Long getId() {
        return id;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }
}