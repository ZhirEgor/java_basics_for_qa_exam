package com.gridDynamics.finalExam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Course {

    private final String courseName;
    private final int courseDuration;

    @Override
    public String toString() {
        return courseName + " " + courseDuration;
    }

}
