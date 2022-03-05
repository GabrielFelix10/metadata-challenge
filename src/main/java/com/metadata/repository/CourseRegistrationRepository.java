package com.metadata.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

    @Query(value = "select count(student_id) from course_registration where student_id = ?1" ,
            nativeQuery = true)
    @JsonIgnore
    long countByStudentId(Long studentId);

    @JsonIgnore
    long countByCourse(Course course);


    Optional<CourseRegistration> findByStudentAndCourse(Student student, Course course);

}
