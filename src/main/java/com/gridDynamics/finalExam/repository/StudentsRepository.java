package com.gridDynamics.finalExam.repository;

import au.com.bytecode.opencsv.CSVReader;
import com.gridDynamics.finalExam.models.Course;
import com.gridDynamics.finalExam.models.Curriculum;
import com.gridDynamics.finalExam.models.Student;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
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

    public List<Student> getStudents() {
        if (students == null) {
            return loadStudentsFromCsvFile();
        } else {
            return students;
        }
    }

    private List<Student> loadStudentsFromCsvFile() {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new AssertionError("CSV file does not contain all required columns, valid file should contain 8");
        } catch (DateTimeParseException e) {
            throw new AssertionError("CSV file contains invalid data format values");
        }
    }
}
