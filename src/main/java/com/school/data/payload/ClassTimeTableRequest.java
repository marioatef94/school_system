package com.school.data.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClassTimeTableRequest {

    private Long schoolId;
    private Long subjectId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime requestDateTime;

    @Override
    public String toString() {
        return "ClassTimeTableRequest{" +
                "schoolId='" + schoolId + '\'' +
                ", subjectId='" + subjectId + '\'' +
                ", requestDateTime=" + requestDateTime +
                '}';
    }
}
