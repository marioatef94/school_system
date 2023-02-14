package com.school.repo;

import com.school.data.model.SchoolClassTime;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface SchoolClassTimeRepo extends CrudRepository<SchoolClassTime,Long> {

    @Query("SELECT COUNT(ct) FROM SchoolClassTime ct " +
            "INNER JOIN Subject s ON s = ct.subject " +
            "INNER JOIN SchoolClass c ON c = ct.schoolClass " +
            "INNER JOIN Grade g ON c.grade = g " +
            "WHERE s.id=?1 AND g.id=?2")
    Long findNumberOfSubjectsPerGrade(Long subjectId,Long gradeId);


}