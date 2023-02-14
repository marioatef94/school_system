package com.school.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "grade")
@NoArgsConstructor
@AllArgsConstructor
public class Grade extends AbstractEntity{

    private String name;
    private String code;


    /************************
     * Relations
     ***********************/
    @JsonIgnore
    @OneToMany(targetEntity = Student.class,mappedBy="grade")
    Set<Student> students;
    @JsonIgnore
    @OneToMany(targetEntity = Student.class,mappedBy="grade")
    Set<SchoolClass> classes;

}
