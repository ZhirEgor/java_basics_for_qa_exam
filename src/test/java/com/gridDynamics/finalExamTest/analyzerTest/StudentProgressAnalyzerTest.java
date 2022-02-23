package com.gridDynamics.finalExamTest.analyzerTest;

import com.gridDynamics.finalExam.reportAnalyser.StudentProgressAnalyser;
import com.gridDynamics.finalExam.repository.StudentsRepository;
import com.gridDynamics.finalExamTest.StudentBaseTest;
import org.junit.jupiter.api.Test;


import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentProgressAnalyzerTest extends StudentBaseTest {

    @Test
    public void validatePositiveTimeDifferenceCorrectly() {
        StudentsRepository mockRepository = mock(StudentsRepository.class);
        when(mockRepository.getStudents()).thenReturn(Arrays.asList(stepanovStepanP));

        StudentProgressAnalyser analyser = new StudentProgressAnalyser(mockRepository);

        analyser.countSpentHours().forEach((student, timeDifference) ->
                assertThat(timeDifference.intValue()).as(String.format("Time difference between current moment and %s should be positive", student.getStartDate())).isPositive());
    }

    @Test
    public void validateNegativeTimeDifferenceCorrectly() {
        StudentsRepository mockRepository = mock(StudentsRepository.class);
        when(mockRepository.getStudents()).thenReturn(Arrays.asList(stepanovStepanF));

        StudentProgressAnalyser analyser = new StudentProgressAnalyser(mockRepository);

        analyser.countSpentHours().forEach((student, timeDifference) ->
                assertThat(timeDifference.intValue()).as(String.format("Time difference between current moment and %s should be negative", student.getStartDate())).isNegative());
    }

}
