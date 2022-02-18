package finalExam;

import au.com.bytecode.opencsv.CSVReader;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StudentsRepository {

    private final String fileLocation;
    private List<Student> students;

    public StudentsRepository(String fileLocation) {
        this.fileLocation = fileLocation;
    }

    private int parseInt(String value) {
        return Integer.parseInt(value.trim());
    }

    public List<Student> loadStudents() {
        if (students == null) {
            students = new ArrayList<>();
            final File file = new File(fileLocation);
            if (!file.exists() || !file.canRead() || !file.isFile()) {
                System.err.println("Unable to find readable file: " + file.getAbsolutePath());
            }
            try (CSVReader reader = new CSVReader(new FileReader(fileLocation))) {
                String[] nextLine;
                while ((nextLine = reader.readNext()) != null) {
                    String studentName = nextLine[0].trim();
                    String curriculumName = nextLine[1].trim();
                    LocalDateTime startDate = LocalDateTime.parse(nextLine[2].trim());
                    Course courseOne = new Course(nextLine[3].trim(), parseInt(nextLine[4]));
                    Course courseTwo = new Course(nextLine[5].trim(), parseInt(nextLine[6]));
                    Course courseThree = new Course(nextLine[7].trim(), parseInt(nextLine[8]));
                    students.add(new Student(studentName,
                            startDate,
                            new Curriculum(curriculumName, Arrays.asList(courseOne, courseTwo, courseThree))));
                }
                return students;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        return students;
    }
}
