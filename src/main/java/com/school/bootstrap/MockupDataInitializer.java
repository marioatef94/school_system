package com.school.bootstrap;

import com.school.data.model.*;
import com.school.repo.*;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Component
public class MockupDataInitializer implements CommandLineRunner {
    // Prefer Isolate In More Than Layer To Separate Logic
    private final static String DEFAULT_SCHOOL_NAME = "TEST_SCHOOL";

    private final SchoolRepo schoolRepo;
    private final SubjectRepo subjectRepo;
    private final SchoolClassRepo schoolClassRepo;
    private final StudentRepo studentRepo;
    private final GradeRepo gradeRepo;
    private final SchoolClassTimeRepo classTimeRepo;

    @Autowired
    public MockupDataInitializer(SchoolRepo schoolRepo, SubjectRepo subjectRepo, SchoolClassRepo schoolClassRepo, StudentRepo studentRepo, GradeRepo gradeRepo, SchoolClassTimeRepo classTimeRepo) {
        this.schoolRepo = schoolRepo;
        this.subjectRepo = subjectRepo;
        this.schoolClassRepo = schoolClassRepo;
        this.studentRepo = studentRepo;
        this.gradeRepo = gradeRepo;
        this.classTimeRepo = classTimeRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        //Using Faker API For Generating Mock Data
        initSchools();
        initSubjects();
        initGrades();
        initStudents();
        initSchoolClasses();
        initSchoolClassesTimeTable();
    }
    private void initSchools(){
        List<School> schoolList = (List<School>) schoolRepo.findAll();
        if(schoolList.size()==0){
            log.info("Saving New School : {}", DEFAULT_SCHOOL_NAME);
            School school = School.builder()
                    .code(UUID.randomUUID().toString())
                    .name(DEFAULT_SCHOOL_NAME)
                    .address(Faker.instance().address().fullAddress())
                    .build();
            schoolRepo.save(school);
            log.info("{} Saved Successfully", DEFAULT_SCHOOL_NAME);
        }
    }
    private void initSubjects(){
        List<Subject> subjectList = (List<Subject>) subjectRepo.findAll();
        if(subjectList.size()==0){
            List<String> subjectNameList = List.of("Mathematics","Science","English","Technology");
            subjectNameList.forEach(subjectName->{
                log.info("Saving Subject : {}", subjectName);
                Subject subject = Subject.builder()
                        .name(subjectName)
                        .code(subjectName.toUpperCase())
                        .build();
                subjectRepo.save(subject);
                log.info("Subject : {} - Saved Successfully", subjectName);
            });
        }
    }
    private void initGrades(){
        List<Grade> gradeList = (List<Grade>) gradeRepo.findAll();
        if(gradeList.size() == 0){
            AtomicInteger counter = new AtomicInteger(1);
            log.info("Saving Grades");
            Set<Grade> gradeSet = Stream.generate(()->
                    Grade.builder()
                        .name("Grade " + counter.get())
                        .code("GRADE-" + counter.getAndIncrement())
                        .build())
                        .limit(5).collect(Collectors.toSet());
            gradeRepo.saveAll(gradeSet);

        }
    }
    private void initStudents(){
        List<Student> students = (List<Student>) studentRepo.findAll();
        if(students.size()==0){
            List<Grade> gradeList = (List<Grade>) gradeRepo.findAll();
            School school = schoolRepo.findByName(DEFAULT_SCHOOL_NAME);
            gradeList.forEach(grade -> {
                log.info("Adding Student For : {}", grade.getName());
                Set<Student> studentSetPerGrade = Stream.generate(()->{
                        String studentNameFirstName = Faker.instance().name().firstName();
                        String studentEmail = studentNameFirstName + "@" + DEFAULT_SCHOOL_NAME.toLowerCase().replace(" ","") + ".edu";
                        return Student.builder()
                                .grade(grade)
                                .school(school)
                                .name(studentNameFirstName +  Faker.instance().name().lastName())
                                .email(studentEmail)
                                .build();
                        }).limit(20).collect(Collectors.toSet());
                studentRepo.saveAll(studentSetPerGrade);
                log.info("Students Added Successfully For {}", grade.getName());
            });
        }
    }
    private void initSchoolClasses(){
        List<SchoolClass> classList = (List<SchoolClass>) schoolClassRepo.findAll();
        if(classList.size()==0){
            List<Grade> gradeList = (List<Grade>) gradeRepo.findAll();
            gradeList.forEach(grade -> {
                log.info("Adding Classes For : {}", grade.getName());
                AtomicInteger counter = new AtomicInteger(1);
                Set<SchoolClass> classSetPerGrade = Stream.generate(()->
                            SchoolClass.builder()
                                    .grade(grade)
                                    .name(grade.getName() + "- Class -" + 0 +"" + counter.get())
                                    .code(grade.getCode() +"-CLASS-0" + "" + counter.getAndIncrement())
                                    .build())
                        .limit(5).collect(Collectors.toSet());
                schoolClassRepo.saveAll(classSetPerGrade);
                log.info("Classes Added Successfully : {}", grade.getName());
            });
        }
    }
    private void initSchoolClassesTimeTable(){
        List<SchoolClassTime> classTimeList = (List<SchoolClassTime>) classTimeRepo.findAll();
        if(classTimeList.size()==0){
            String currentDateTimeInString = LocalDate.now() + " 08:00";
            LocalDateTime currentDateTime = convertStringToLocalDateTime(currentDateTimeInString);


            List<SchoolClass> classList = (List<SchoolClass>) schoolClassRepo.findAll();
            List<Subject> subjectList = (List<Subject>) subjectRepo.findAll();
            classList.forEach(schoolClass->{
                log.info("Add Class Time Table For {}", schoolClass.getName());
                AtomicReference<LocalDateTime> timeAtomicReference = new AtomicReference<>(currentDateTime);
                subjectList.forEach(subject -> {
                    SchoolClassTime schoolClassTime = SchoolClassTime.builder()
                            .code(schoolClass.getCode() + " " + timeAtomicReference.get())
                            .dateTime(timeAtomicReference.get())
                            .schoolClass(schoolClass)
                            .subject(subject)
                            .build();
                    classTimeRepo.save(schoolClassTime);
                    timeAtomicReference.set(timeAtomicReference.get().plusMinutes(30)); //Maybe Consider Duplication Of Subject In Same Time For Same Grade
                });
                log.info("Class Time Table For {} , has been added successfully", schoolClass.getName() );
            });
        }
    }

    //Prefer To Create Date Util
    private LocalDateTime convertStringToLocalDateTime(String Date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(Date, formatter);
    }
}
