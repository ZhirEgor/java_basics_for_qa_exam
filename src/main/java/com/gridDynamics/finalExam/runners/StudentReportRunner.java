package com.gridDynamics.finalExam.runners;

import com.gridDynamics.finalExam.reportAnalyser.StudentProgressAnalyser;
import com.gridDynamics.finalExam.models.StudentReport;
import com.gridDynamics.finalExam.repository.StudentsRepository;

import java.io.PrintStream;

import static com.gridDynamics.finalExam.enums.ReportType.getReportType;

public class StudentReportRunner {

    public static void run(PrintStream output, int reportType, String[] arguments) {

        String fileLocation = arguments[0];
        StudentsRepository repo = new StudentsRepository(fileLocation);
        StudentProgressAnalyser analyser = new StudentProgressAnalyser(repo);
        StudentReport report = new StudentReport(output, analyser.countSpentHours(),getReportType(reportType));
        report.run();

    }

}
