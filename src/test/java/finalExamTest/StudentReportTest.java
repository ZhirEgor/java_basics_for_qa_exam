package finalExamTest;

import finalExam.*;
import org.junit.Test;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.*;

public class StudentReportTest {

    public static final String STUDENT_NAME = "Stepanov Stepan";
    public static final String CURRICULUM_NAME = "QA Engineer";
    public static final LocalDateTime START_DATE = LocalDateTime.parse("2022-02-01T10:00:00");
    public static final String COURSE_ONE_NAME = "Spring";
    public static final int COURSE_ONE_DURATION = 10;
    public static final String COURSE_TWO_NAME = "Java";
    public static final int COURSE_TWO_DURATION = 15;
    public static final String COURSE_THREE_NAME = "SQL";
    public static final int COURSE_THREE_DURATION = 20;
    public static List<Course> courseList = Arrays.asList(
            new Course(COURSE_ONE_NAME, COURSE_ONE_DURATION),
            new Course(COURSE_TWO_NAME, COURSE_TWO_DURATION),
            new Course(COURSE_THREE_NAME, COURSE_THREE_DURATION)
    );
    public Curriculum curriculum = new Curriculum(CURRICULUM_NAME, courseList);
    public Student stepanovStepan = new Student(STUDENT_NAME, START_DATE, curriculum);

    PrintStream mockOutput = mock(PrintStream.class);
    StudentProgressAnalyser analyser = mock(StudentProgressAnalyser.class);
    StudentReport reportShort = new StudentReport(mockOutput, analyser, "short");
    StudentReport reportLong = new StudentReport(mockOutput, analyser, "long");

    @Test
    public void validateGenerateShortStudentReport() {
        Map<Student, Long> results = new HashMap<>();
        results.put(stepanovStepan, 50L);
        when(analyser.countSpentHours()).thenReturn(results);

        reportShort.run();

        verify(mockOutput).printf(anyString(), eq(STUDENT_NAME), eq(CURRICULUM_NAME), eq(50L));
    }

    @Test
    public void validateGenerateLongStudentReport() {
        Map<Student, Long> results = new HashMap<>();
        results.put(stepanovStepan, 50L);
        when(analyser.countSpentHours()).thenReturn(results);

        reportLong.run();

        verify(mockOutput).printf(anyString(), eq(STUDENT_NAME));
        verify(mockOutput).printf(anyString(), eq(CURRICULUM_NAME));
        verify(mockOutput).printf(anyString(), eq(START_DATE.toString()));
        verify(mockOutput).printf(anyString(), eq(50L));
        verify(mockOutput).printf(anyString(), eq(1), eq(COURSE_ONE_NAME), eq(COURSE_ONE_DURATION));
        verify(mockOutput).printf(anyString(), eq(2), eq(COURSE_TWO_NAME), eq(COURSE_TWO_DURATION));
        verify(mockOutput).printf(anyString(), eq(3), eq(COURSE_THREE_NAME), eq(COURSE_THREE_DURATION));
    }


}
