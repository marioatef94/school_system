package com.school.repo;

import com.school.data.model.School;
import org.springframework.data.repository.CrudRepository;
public interface SchoolRepo extends CrudRepository<School,Long> {

    School findByName(String name);

}