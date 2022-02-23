package com.gridDynamics.finalExamTest;

import com.gridDynamics.finalExam.models.Course;
import com.gridDynamics.finalExam.models.Curriculum;
import com.gridDynamics.finalExam.models.Student;
import com.gridDynamics.finalExam.reportAnalyser.StudentProgressAnalyser;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public abstract class StudentBaseTest {

    protected static final String STUDENT_NAME = "Stepanov Stepan";
    protected static final String CURRICULUM_NAME = "QA Engineer";
    protected static final LocalDateTime START_DATE_PAST = LocalDateTime.parse("2022-02-01T10:00:00");
    protected static final LocalDateTime START_DATE_FUTURE = LocalDateTime.parse("2022-03-01T10:00:00");
    protected static final String COURSE_ONE_NAME = "Spring";
    protected static final int COURSE_ONE_DURATION = 10;
    protected static final String COURSE_TWO_NAME = "Java";
    protected static final int COURSE_TWO_DURATION = 15;
    protected static final String COURSE_THREE_NAME = "SQL";
    protected static final int COURSE_THREE_DURATION = 20;
    protected static List<Course> courseList = Arrays.asList(
            new Course(COURSE_ONE_NAME, COURSE_ONE_DURATION),
            new Course(COURSE_TWO_NAME, COURSE_TWO_DURATION),
            new Course(COURSE_THREE_NAME, COURSE_THREE_DURATION)
    );
    protected Curriculum curriculum = new Curriculum(CURRICULUM_NAME, courseList);
    protected Student stepanovStepanP = new Student(STUDENT_NAME, START_DATE_PAST, curriculum);
    protected Student stepanovStepanF = new Student(STUDENT_NAME, START_DATE_FUTURE, curriculum);

    protected PrintStream mockOutput = mock(PrintStream.class);
    protected StudentProgressAnalyser analyser = mock(StudentProgressAnalyser.class);

    protected Map<Student, Long> results = new HashMap<>();

}
