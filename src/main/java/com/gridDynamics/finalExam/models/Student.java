package com.gridDynamics.finalExam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Student {

    private final String studentName;
    private final LocalDateTime startDate;
    private final Curriculum curriculum;

}
