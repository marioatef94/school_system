package com.school.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
public class Student extends AbstractEntity{

    private String name;
    private String email;

    /************************
     * Relations
     ***********************/

    @ManyToOne(targetEntity = Grade.class)
    @JoinColumn(name="grade_id")
    private Grade grade;
    @ManyToOne(targetEntity = School.class)
    @JoinColumn(name="school_id")
    private School school;

}
