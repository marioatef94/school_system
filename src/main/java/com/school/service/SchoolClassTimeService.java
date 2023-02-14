package com.school.service;

import com.school.data.model.SchoolClassTime;
import com.school.repo.SchoolClassTimeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Qualifier("class_time")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class SchoolClassTimeService implements GenericService<SchoolClassTime> {

    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer)
    private final SchoolClassTimeRepo classTimeRepo;

    @Autowired
    public SchoolClassTimeService(SchoolClassTimeRepo classTimeRepo) {
        this.classTimeRepo = classTimeRepo;
    }

    @Override
    public SchoolClassTime save(SchoolClassTime schoolClassTime) {
        return classTimeRepo.save(schoolClassTime);
    }

    @Override
    public SchoolClassTime findById(Long id) {
        var optionalClassTime = classTimeRepo.findById(id);
        return optionalClassTime.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public SchoolClassTime update(SchoolClassTime schoolClassTime) {
        return classTimeRepo.save(schoolClassTime);
    }

    @Override
    public List<SchoolClassTime> findAll() {
        return (List<SchoolClassTime>) classTimeRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        classTimeRepo.deleteById(id);
    }
}
