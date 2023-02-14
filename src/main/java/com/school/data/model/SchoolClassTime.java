package com.school.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@Table(name = "class_time")
@NoArgsConstructor
@AllArgsConstructor
public class SchoolClassTime extends AbstractEntity {

    private String code;
    private LocalDateTime dateTime;

    /************************
     * Relations
     ***********************/
    @ManyToOne(targetEntity = Subject.class)
    @JoinColumn(name="subject_id")
    private Subject subject;
    @JsonIgnore
    @ManyToOne(targetEntity = SchoolClass.class)
    @JoinColumn(name="class_id")
    private SchoolClass schoolClass;
}
