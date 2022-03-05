package com.metadata.repository;

import com.metadata.model.Course;
import com.metadata.model.CourseRegistration;
import com.metadata.model.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

    long countByStudent(Student student);

    long countByCourse(Course course);
}
