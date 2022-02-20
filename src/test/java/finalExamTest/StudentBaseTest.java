package finalExamTest;

import finalExam.*;

import java.io.PrintStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.Mockito.mock;

public abstract class StudentBaseTest {

    public static final String STUDENT_NAME = "Stepanov Stepan";
    public static final String CURRICULUM_NAME = "QA Engineer";
    public static final LocalDateTime START_DATE_PAST = LocalDateTime.parse("2022-02-01T10:00:00");
    public static final LocalDateTime START_DATE_FUTURE = LocalDateTime.parse("2022-03-01T10:00:00");
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
    public Student stepanovStepanP = new Student(STUDENT_NAME, START_DATE_PAST, curriculum);
    public Student stepanovStepanF = new Student(STUDENT_NAME, START_DATE_FUTURE, curriculum);

    PrintStream mockOutput = mock(PrintStream.class);
    StudentProgressAnalyser analyser = mock(StudentProgressAnalyser.class);
    StudentReport reportShort = new StudentReport(mockOutput, analyser, "short");
    StudentReport reportLong = new StudentReport(mockOutput, analyser, "long");

    Map<Student, Long> results = new HashMap<>();

}
