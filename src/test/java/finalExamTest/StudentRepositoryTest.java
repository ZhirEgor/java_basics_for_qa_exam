package finalExamTest;

import finalExam.Student;
import finalExam.StudentsRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.util.List;

public class StudentRepositoryTest {

    @Test
    public void shouldLoadSampleData() {

        StudentsRepository repository = new StudentsRepository("src/main/resources/students.csv");

        List<Student> students = repository.loadStudents();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(students.get(0)).as("Student name does not equal Ivanov Ivan").hasFieldOrPropertyWithValue("studentName", "Ivanov Ivan");

    }
}
