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
@Table(name = "subject")
@NoArgsConstructor
@AllArgsConstructor
public class Subject extends AbstractEntity{

    private String code;
    private String name;
    /************************
     * Relations
     ***********************/
    @JsonIgnore
    @OneToMany(targetEntity = SchoolClassTime.class,mappedBy="subject")
    Set<SchoolClassTime> classTimeSet;
}
