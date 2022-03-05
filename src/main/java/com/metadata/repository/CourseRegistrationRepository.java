package com.metadata.repository;

import com.metadata.model.CourseRegistration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends CrudRepository<CourseRegistration, Long> {

}
