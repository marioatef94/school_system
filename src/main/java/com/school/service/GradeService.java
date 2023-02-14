package com.school.service;

import com.school.data.model.Grade;
import com.school.repo.GradeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("grade")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class GradeService implements GenericService<Grade> {
    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer )
    private final GradeRepo gradeRepo;

    @Autowired
    public GradeService(GradeRepo gradeRepo) {
        this.gradeRepo = gradeRepo;
    }


    @Override
    public Grade save(Grade grade) {
        return gradeRepo.save(grade);
    }

    @Override
    public Grade findById(Long id) {
        var optionalGrade = gradeRepo.findById(id);
        return optionalGrade.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public Grade update(Grade grade) {
        return gradeRepo.save(grade);
    }

    @Override
    public List<Grade> findAll() {
        return (List<Grade>) gradeRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        gradeRepo.deleteById(id);
    }
}



