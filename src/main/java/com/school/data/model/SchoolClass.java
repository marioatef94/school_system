package com.school.data.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "class")
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClass extends AbstractEntity{

    private String code;
    private String name;

    /************************
     * Relations
     ***********************/
    @ManyToOne(targetEntity = Grade.class)
    @JoinColumn(name="grade_id")
    private Grade grade;
    @OneToMany(targetEntity = SchoolClassTime.class,mappedBy="schoolClass")
    Set<SchoolClassTime> classTimeSet;

}
