package com.gridDynamics.finalExam.models;

import com.gridDynamics.finalExam.enums.ReportType;
import lombok.AllArgsConstructor;

import java.io.PrintStream;
import java.util.Map;

import static com.gridDynamics.finalExam.enums.ReportType.SHORT;

@AllArgsConstructor
public class StudentReport {

    private final PrintStream output;
    private final Map<Student, Long> studentStatusMap;
    private final ReportType reportType;

    public void run() {
        if (reportType.equals(SHORT)) {
            generateShortReport();
        } else {
            generateFullReport();
        }
    }

    public void generateShortReport() {
        studentStatusMap.forEach((student, timeDifference) ->
        {
            if (timeDifference < 0) {
                output.printf("%s (%s) - Training is not finished. %o working hours are left until the end.\n", student.getStudentName(), student.getCurriculum().getCurriculumName(), Math.abs(timeDifference));
            } else {
                output.printf("%s (%s) - Training completed. %o hours have passed since the end.\n", student.getStudentName(), student.getCurriculum().getCurriculumName(), timeDifference);
            }
        });
    }

    public void generateFullReport() {
        studentStatusMap.forEach((student, timeDifference) ->
        {
            output.printf("\nSTUDENT: %s ", student.getStudentName());
            output.printf("\nCURRICULUM: %s", student.getCurriculum().getCurriculumName());
            output.printf("\nSTART_DATE : %s", student.getStartDate().toString());
            if (timeDifference < 0) {
                output.printf("\nTraining is not finished. \n%s working hours are left until the end.", Math.abs(timeDifference));
            } else {
                output.printf("\nTraining completed. \n%s working hours have passed since the end.", timeDifference);
            }
            output.println("\nCOURSE     DURATION(hrs)");
            output.println("\n______________________");
            for (int i = 0; i < student.getCurriculum().getCourseList().size(); i++) {
                Course course = student.getCurriculum().getCourseList().get(i);
                output.printf("\n%s. %s          %s", i + 1, course.getCourseName(), course.getCourseDuration());
            }
        });
    }

}
