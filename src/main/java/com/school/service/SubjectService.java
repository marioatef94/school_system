package com.school.service;

import com.school.data.model.Subject;
import com.school.repo.SubjectRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("subject")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class SubjectService implements GenericService<Subject> {

    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer
    private final SubjectRepo subjectRepo;
    @Autowired
    public SubjectService(SubjectRepo subjectRepo) {
        this.subjectRepo = subjectRepo;
    }

    @Override
    public Subject save(Subject subject) {
        return subjectRepo.save(subject);
    }

    @Override
    public Subject findById(Long id) {
        var optionalSubject = subjectRepo.findById(id);
        return optionalSubject.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public Subject update(Subject subject) {
        return subjectRepo.save(subject);
    }

    @Override
    public List<Subject> findAll() {
        return (List<Subject>) subjectRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        subjectRepo.deleteById(id);
    }
}
