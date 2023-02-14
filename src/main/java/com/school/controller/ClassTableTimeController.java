package com.school.controller;

import com.school.data.constants.Constants;
import com.school.data.model.SchoolClass;
import com.school.data.payload.ClassTimeTableRequest;
import com.school.repo.SchoolClassRepo;
import com.school.repo.SchoolClassTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(Constants.SYSTEM_API_VERSION_01_PATH + "/schools")
public class ClassTableTimeController {

    private final SchoolClassRepo schoolClassRepo; //Should Inject Service Instead But Due to the requirements is Just CRUD , I Created Generic Service
    private final SchoolClassTimeRepo schoolClassTimeRepo;

    @Autowired
    public ClassTableTimeController(SchoolClassRepo schoolClassRepo, SchoolClassTimeRepo schoolClassTimeRepo) {
        this.schoolClassRepo = schoolClassRepo;
        this.schoolClassTimeRepo = schoolClassTimeRepo;
    }


    // May Add Swagger For API Documentation
    @PostMapping("/classes/times") // Prefer To Create Structured Entity For Unify Response
    public ResponseEntity<List<SchoolClass>> findProperClassTimeTable(@RequestBody ClassTimeTableRequest timeTableRequest){
        //Apply Validation For Null ANd Business Logic ( In Service Layer) And Handle Exceptions
        var classList = schoolClassRepo.findBySubjectAndTime(timeTableRequest.getRequestDateTime(),timeTableRequest.getSubjectId());
        return ResponseEntity.ok(classList);
    }

    @GetMapping("/grades/{gradeId}/subjects/{subjectId}")
    public ResponseEntity<Long> findNumberOfSubjectsPerGrade(@PathVariable("subjectId")Long subjectId,
                                                             @PathVariable("gradeId")Long gradeId){
        var list =  schoolClassTimeRepo.findNumberOfSubjectsPerGrade(subjectId,gradeId);
        return ResponseEntity.ok(list);
    }



}
