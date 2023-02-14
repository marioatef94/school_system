package com.school.service;

import com.school.data.model.SchoolClass;
import com.school.repo.SchoolClassRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("school_class")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class SchoolClassService implements GenericService<SchoolClass> {

    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer )
    private final SchoolClassRepo schoolClassRepo;

    @Autowired
    public SchoolClassService(SchoolClassRepo schoolClassRepo) {
        this.schoolClassRepo = schoolClassRepo;
    }

    @Override
    public SchoolClass save(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }

    @Override
    public SchoolClass findById(Long id) {
        var optionalSchoolClass = schoolClassRepo.findById(id);
        return optionalSchoolClass.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public SchoolClass update(SchoolClass schoolClass) {
        return schoolClassRepo.save(schoolClass);
    }

    @Override
    public List<SchoolClass> findAll() {
        return (List<SchoolClass>) schoolClassRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        schoolClassRepo.deleteById(id);
    }
}
