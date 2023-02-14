package com.school.service;

import com.school.data.model.Student;
import com.school.repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Qualifier("student")//Should Be DTO Instead Of Entity But For Simplicity I Use Entity
public class StudentService implements GenericService<Student> {

    // Require Validation If Needed ( Prefer To Have Isolated Validation Layer )
    private final StudentRepo studentRepo;

    @Autowired
    public StudentService(StudentRepo studentRepo) {
        this.studentRepo = studentRepo;
    }

    @Override
    public Student save(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public Student findById(Long id) {
        var optionalStudent = studentRepo.findById(id);
        return optionalStudent.orElseThrow(()-> new RuntimeException("Not Found Exception")); //Prefer To Throw Custom Exception
    }

    @Override
    public Student update(Student student) {
        return studentRepo.save(student);
    }

    @Override
    public List<Student> findAll() {
        return (List<Student>) studentRepo.findAll();
    }

    @Override
    public void delete(Long id) {
        //Prefer to Return boolean
        studentRepo.deleteById(id);
    }
}
