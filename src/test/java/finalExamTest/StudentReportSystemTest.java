package finalExamTest;

import finalExam.ApplicationRunner;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class StudentReportSystemTest {

    @Test
    public void shortReportOutputValidation() {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run("src/main/resources/students.csv", "short");

        assertThat(output).as("Training message is wrong").contains("Trai213ning");
    }

    @Test
    public void longReportOutputValidation() {
        ApplicationRunner runner = new ApplicationRunner();

        String output = runner.run("src/main/resources/students.csv", "long");

        assertThat(output).as("Training message is wrong").contains("Trai213ning");
    }

}
