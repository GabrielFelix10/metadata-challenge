package com.metadata.repository;

import com.metadata.model.Course;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "courses", path = "course")
public interface CourseRepository extends PagingAndSortingRepository<Course, Long> {

}
