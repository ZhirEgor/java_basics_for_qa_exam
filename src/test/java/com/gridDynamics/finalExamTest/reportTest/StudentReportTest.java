package com.gridDynamics.finalExamTest.reportTest;

import com.gridDynamics.finalExam.models.StudentReport;
import com.gridDynamics.finalExamTest.StudentBaseTest;
import org.junit.jupiter.api.Test;

import static com.gridDynamics.finalExam.enums.ReportType.LONG;
import static com.gridDynamics.finalExam.enums.ReportType.SHORT;
import static org.mockito.Mockito.*;

public class StudentReportTest extends StudentBaseTest {

    @Test
    public void validateGenerateShortStudentReport() {
        results.put(stepanovStepanP, 50L);
        when(analyser.countSpentHours()).thenReturn(results);

        StudentReport reportShort = new StudentReport(mockOutput, analyser.countSpentHours(), SHORT);
        reportShort.run();

        verify(mockOutput).printf(anyString(), eq(STUDENT_NAME), eq(CURRICULUM_NAME), eq(50L));
    }

    @Test
    public void validateGenerateLongStudentReport() {
        results.put(stepanovStepanP, 50L);
        when(analyser.countSpentHours()).thenReturn(results);

        StudentReport reportLong = new StudentReport(mockOutput, analyser.countSpentHours(), LONG);
        reportLong.run();

        verify(mockOutput).printf(anyString(), eq(STUDENT_NAME));
        verify(mockOutput).printf(anyString(), eq(CURRICULUM_NAME));
        verify(mockOutput).printf(anyString(), eq(START_DATE_PAST.toString()));
        verify(mockOutput).printf(anyString(), eq(50L));
        verify(mockOutput).printf(anyString(), eq(1), eq(COURSE_ONE_NAME), eq(COURSE_ONE_DURATION));
        verify(mockOutput).printf(anyString(), eq(2), eq(COURSE_TWO_NAME), eq(COURSE_TWO_DURATION));
        verify(mockOutput).printf(anyString(), eq(3), eq(COURSE_THREE_NAME), eq(COURSE_THREE_DURATION));
    }


}
