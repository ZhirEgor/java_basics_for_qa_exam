package finalExam;

import java.time.LocalDateTime;

public class Student {

    private final String studentName;
    private final LocalDateTime startDate;
    private final Curriculum curriculum;

    public Student(String studentName, LocalDateTime startDate, Curriculum curriculum) {
        this.studentName = studentName;
        this.startDate = startDate;
        this.curriculum = curriculum;
    }

    public String getStudentName() {
        return studentName;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public Curriculum getCurriculum() {
        return curriculum;
    }

}
