package com.metadata.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

    @Query(value = "select count(student_id) from course_registration where student_id = ?1" ,
            nativeQuery = true)
    @JsonIgnore
    long countByStudentId(Long studentId);

    @Query(value = "select count(course_id) from course_registration where course_id = ?1" ,
            nativeQuery = true)
    @JsonIgnore
    long countByCourse(Long courseId);


    Optional<CourseRegistration> findByStudentAndCourse(Student student, Course course);

    Optional<List<CourseRegistration>> findAllByStudent(Student student);
    Optional<List<CourseRegistration>> findAllByCourse(Course course);

}
