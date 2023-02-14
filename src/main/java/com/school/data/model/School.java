package com.school.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@Builder
@Entity
@Table(name = "school")
@NoArgsConstructor
@AllArgsConstructor
public class School extends AbstractEntity {

    private String code;
    private String name;
    private String address;

    /************************
     * Relations
     ***********************/
    @OneToMany(targetEntity = Student.class,mappedBy="school")
    Set<Student> students;

}
