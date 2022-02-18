package finalExam;

public class Course {

    private final String courseName;
    private final int courseDuration;

    public Course(String courseName, int courseDuration) {
        this.courseName = courseName;
        this.courseDuration = courseDuration;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseDuration() {
        return courseDuration;
    }

}
