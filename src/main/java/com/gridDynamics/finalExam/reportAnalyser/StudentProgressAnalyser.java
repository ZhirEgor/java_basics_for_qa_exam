package com.gridDynamics.finalExam.reportAnalyser;

import com.gridDynamics.finalExam.models.Student;
import com.gridDynamics.finalExam.repository.StudentsRepository;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentProgressAnalyser {

    private final StudentsRepository repository;

    public StudentProgressAnalyser(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Student, Long> countSpentHours() {
        return repository.getStudents()
                .stream()
                .collect(Collectors.toMap(p -> p,
                        t -> new TimeDiffCalculator()
                                .countDifferenceBetweenFinishDateAndCurrentTime(t.getStartDate(), LocalDateTime.now()) - t.getCurriculum().getCurriculumDuration()));
    }

}
