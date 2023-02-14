package com.school.repo;

import com.school.data.model.Grade;
import org.springframework.data.repository.CrudRepository;

public interface GradeRepo extends CrudRepository<Grade,Long> {
}
