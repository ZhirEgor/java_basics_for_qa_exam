package com.gridDynamics.finalExamTest.repositoryTest;

import com.gridDynamics.finalExam.models.Course;
import com.gridDynamics.finalExam.models.Student;
import com.gridDynamics.finalExam.repository.StudentsRepository;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

public class StudentRepositoryTest {

    @Test
    public void shouldLoadSampleDataTest() {

        StudentsRepository repository = new StudentsRepository("src/main/resources/students.csv");

        List<Student> students = repository.getStudents();
        Student ivanovIvan = students.get(0);
        Student sidorovIvan = students.get(1);

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(ivanovIvan.getStudentName()).as(String.format("Student name is not equal %s", ivanovIvan.getStudentName())).isEqualTo("Ivanov Ivan");
        softAssertions.assertThat(ivanovIvan.getStartDate()).as(String.format("Start date is not equal %s", ivanovIvan.getStartDate())).isEqualTo(LocalDateTime.parse("2022-03-01T10:00:00"));
        List<Course> ivanIvanovCourseList = ivanovIvan.getCurriculum().getCourseList();
        softAssertions.assertThat(ivanIvanovCourseList.get(0).toString())
                .as(String.format("First course is not equal %s", ivanIvanovCourseList.get(0).toString())).isEqualTo("Java 16");
        softAssertions.assertThat(ivanIvanovCourseList.get(1).toString())
                .as(String.format("Second course is not equal %s", ivanIvanovCourseList.get(1).toString())).isEqualTo("JDBC 24");
        softAssertions.assertThat(ivanIvanovCourseList.get(2).toString())
                .as(String.format("Third course is not equal %s", ivanIvanovCourseList.get(2).toString())).isEqualTo("Spring 16");

        softAssertions.assertThat(sidorovIvan.getStudentName()).as(String.format("Student name is not equal %s", sidorovIvan.getStudentName())).isEqualTo("Sidorov Ivan");
        softAssertions.assertThat(sidorovIvan.getStartDate()).as(String.format("Start date is not equal %s", sidorovIvan.getStartDate())).isEqualTo(LocalDateTime.parse("2022-02-01T10:00:00"));
        List<Course> sidorovIvanCourseList = sidorovIvan.getCurriculum().getCourseList();
        softAssertions.assertThat(sidorovIvanCourseList.get(0).toString())
                .as(String.format("First course is not equal %s", sidorovIvanCourseList.get(0).toString())).isEqualTo("Test design 10");
        softAssertions.assertThat(sidorovIvanCourseList.get(1).toString())
                .as(String.format("Second course is not equal %s", sidorovIvanCourseList.get(1).toString())).isEqualTo("Page Object 16");
        softAssertions.assertThat(sidorovIvanCourseList.get(2).toString())
                .as(String.format("Third course is not equal %s", sidorovIvanCourseList.get(2).toString())).isEqualTo("Selenium 16");

        softAssertions.assertAll();

    }

    @ParameterizedTest(name = "Validate that StudentRepository class handles unexpected data in csv file")
    @MethodSource("getInvalidCsvListTestData")
    public void shouldHandleInvalidDataTest(String fileLocation, String exceptionMessage) {
        StudentsRepository repository = new StudentsRepository(fileLocation);

        AssertionError thrown = Assertions.assertThrows(AssertionError.class, () -> {
            List<Student> students = repository.getStudents();
        });

        assertThat(thrown.getMessage()).as("Exception message doesn't match thrown exception type").isEqualTo(String.format(exceptionMessage));
    }

    protected static Stream<Arguments> getInvalidCsvListTestData() {
        return Stream.of(
                Arguments.of("src/main/resources/studentsInvalidDate.csv", "CSV file contains invalid data format values"),
                Arguments.of("src/main/resources/studentsInvalidCourseList.csv", "CSV file does not contain all required columns, valid file should contain 8")
        );
    }
}
