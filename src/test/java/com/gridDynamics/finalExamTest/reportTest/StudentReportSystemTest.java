package com.gridDynamics.finalExamTest.reportTest;

import com.gridDynamics.finalExam.runners.ApplicationRunner;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

public class StudentReportSystemTest {

    @Test
    public void shortReportOutputValidation() {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run(0, "src/main/resources/students.csv");

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(output).as("Sidorov Ivan short report curriculum message is wrong").contains("Sidorov Ivan (AQE) - Training completed.");
        softAssertions.assertThat(output).as("Sidorov Ivan short report hours counting is wrong").contains("hours have passed since the end.");

        softAssertions.assertThat(output).as("Ivanov Ivan short report curriculum message is wrong").contains("Ivanov Ivan (Java Developer) - Training is not finished.");
        softAssertions.assertThat(output).as("Ivanov Ivan short report hours counting is wrong").contains("working hours are left until the end.");

        softAssertions.assertAll();
    }

    @Test
    public void longReportOutputValidation() {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run(1, "src/main/resources/students.csv");

        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(output).as("Sidorov Ivan long report name message is wrong").contains("STUDENT: Sidorov Ivan");
        softAssertions.assertThat(output).as("Sidorov Ivan long report curriculum message is wrong").contains("CURRICULUM: AQE");
        softAssertions.assertThat(output).as("Sidorov Ivan long report start date message is wrong").contains("START_DATE : 2022");
        softAssertions.assertThat(output).as("Sidorov Ivan long report completion message is wrong").contains("Training completed.");
        softAssertions.assertThat(output).as("Sidorov Ivan long report hours counting is wrong").contains("working hours have passed since the end.");
        softAssertions.assertThat(output).as("Sidorov Ivan long report course-duration header is wrong").contains("COURSE     DURATION(hrs)");
        softAssertions.assertThat(output).as("Sidorov Ivan long report underscore line is wrong").contains("______________________");
        softAssertions.assertThat(output).as("Sidorov Ivan long report first course message is wrong").contains("1. Test design          10");
        softAssertions.assertThat(output).as("Sidorov Ivan long report second course message is wrong").contains("2. Page Object          16");
        softAssertions.assertThat(output).as("Sidorov Ivan long report third course message is wrong").contains("3. Selenium          16");

        softAssertions.assertThat(output).as("Ivanov Ivan long report name message is wrong").contains("STUDENT: Ivanov Ivan ");
        softAssertions.assertThat(output).as("Ivanov Ivan long report curriculum message is wrong").contains("CURRICULUM: Java Developer");
        softAssertions.assertThat(output).as("Ivanov Ivan long report start date message is wrong").contains("START_DATE : 2022");
        softAssertions.assertThat(output).as("Ivanov Ivan long report completion message is wrong").contains("Training is not finished.");
        softAssertions.assertThat(output).as("Ivanov Ivan long report hours counting is wrong").contains("working hours are left until the end.");
        softAssertions.assertThat(output).as("Ivanov Ivan long report course-duration header is wrong").contains("COURSE     DURATION(hrs)");
        softAssertions.assertThat(output).as("Ivanov Ivan long report underscore line is wrong").contains("______________________");
        softAssertions.assertThat(output).as("Ivanov Ivan long report first course message is wrong").contains("1. Java          16");
        softAssertions.assertThat(output).as("Ivanov Ivan long report second course message is wrong").contains("2. JDBC          24");
        softAssertions.assertThat(output).as("Ivanov Ivan long report third course message is wrong").contains("3. Spring          16");

        softAssertions.assertAll();
    }
}
