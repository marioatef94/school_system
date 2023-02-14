package com.school.repo;

import com.school.data.model.SchoolClass;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;


public interface SchoolClassRepo extends CrudRepository<SchoolClass,Long> {

    @Query(value = "SELECT c FROM SchoolClass c" +
            " INNER JOIN SchoolClassTime ct ON ct.schoolClass = c" +
            " INNER JOIN Subject s ON s = ct.subject" +
            " WHERE ct.dateTime=?1 AND s.id =?2")
    List<SchoolClass> findBySubjectAndTime(LocalDateTime dateTime,Long subjectId);

}
