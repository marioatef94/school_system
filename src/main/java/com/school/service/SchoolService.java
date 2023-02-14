package com.school.service;

import com.school.data.model.School;
import com.school.repo.SchoolRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("school")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class SchoolService implements GenericService<School>  {

    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer )
    private final SchoolRepo schoolRepo;
    @Autowired
    public SchoolService(SchoolRepo schoolRepo) {
        this.schoolRepo = schoolRepo;
    }

    @Override
    public School save(School school) {
        return schoolRepo.save(school);
    }

    @Override
    public School findById(Long id) {
        var optionalSchool = schoolRepo.findById(id);
        return optionalSchool.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public School update(School school) {
        return schoolRepo.save(school);
    }

    @Override
    public List<School> findAll() {
        return (List<School>) schoolRepo.findAll();
    }


    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        schoolRepo.deleteById(id);
    }
}
