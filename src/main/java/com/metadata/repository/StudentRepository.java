package com.metadata.repository;

import com.metadata.model.Student;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource(collectionResourceRel = "students", path = "student")
public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {

}
