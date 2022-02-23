package com.gridDynamics.finalExam.models;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class Curriculum {

    private final String curriculumName;
    private final List<Course> courseList;

    public int getCurriculumDuration() {
        return courseList.stream().mapToInt(a -> a.getCourseDuration()).sum();
    }

}