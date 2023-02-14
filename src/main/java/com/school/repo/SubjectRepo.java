package com.school.repo;

import com.school.data.model.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRepo extends CrudRepository<Subject,Long> {
}
