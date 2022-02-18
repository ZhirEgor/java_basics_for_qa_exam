package finalExam;

import java.io.PrintStream;

public class StudentReport {

    private final PrintStream output;
    private final StudentProgressAnalyser analyser;
    private final String reportType;

    public StudentReport(final PrintStream output, StudentProgressAnalyser analyser, String reportType) {
        this.output = output;
        this.analyser = analyser;
        this.reportType = reportType;
    }

    public void run() {
        if (reportType.equals("short")) {
            generateShortReport();
        } else {
            generateFullReport();
        }
    }

    public void generateShortReport() {
        analyser.countSpentHours().forEach((student, timeDifference) ->
        {
            if (timeDifference < 0) {
                output.printf("%s (%s) - Training is not finished. %o working hours are left until the end.\n", student.getStudentName(), student.getCurriculum().getCurriculumName(), Math.abs(timeDifference));
            } else {
                output.printf("%s (%s) - Training completed. %o hours have passed since the end.\n", student.getStudentName(), student.getCurriculum().getCurriculumName(), timeDifference);
            }
        });
    }

    public void generateFullReport() {
        analyser.countSpentHours().forEach((student, timeDifference) ->
        {
            output.printf("\nSTUDENT: %s ", student.getStudentName());
            output.printf("\nCURRICULUM: %s", student.getCurriculum().getCurriculumName());
            output.printf("\nSTART_DATE : %s", student.getStartDate().toString());
            if (timeDifference < 0) {
                output.printf("\nTraining is not finished. \n%o working hours are left until the end.", Math.abs(timeDifference));
            } else {
                output.printf("\nTraining completed. \n%o hours have passed since the end.", timeDifference);
            }
            output.printf("\nCOURSE     DURATION(hrs)");
            output.printf("\n______________________");
            for (int i = 0; i < student.getCurriculum().getCourseList().size(); i++) {
                Course course = student.getCurriculum().getCourseList().get(i);
                output.printf("\n%o. %s          %o", i + 1, course.getCourseName(), course.getCourseDuration());
            }
        });
    }

}
