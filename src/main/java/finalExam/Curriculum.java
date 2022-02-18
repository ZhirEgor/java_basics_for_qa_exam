package finalExam;

import java.util.List;

public class Curriculum {

    private final String curriculumName;
    private final List<Course> courseList;

    public Curriculum(String curriculumName, List<Course> courseList) {
        this.curriculumName = curriculumName;
        this.courseList = courseList;
    }

    public String getCurriculumName() {
        return curriculumName;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public int getCurriculumDuration() {
        return courseList.stream().mapToInt(a -> a.getCourseDuration()).sum();
    }

}