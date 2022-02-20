package finalExam;

import java.io.PrintStream;

public class StudentReportRunner {

    public static void run(PrintStream output, String[] arguments) {
        String fileLocation = arguments[0];
        String reportType;
        if (arguments.length > 1) {
            reportType = arguments[1];
        } else {
            reportType = "short";
        }
        StudentsRepository repo = new StudentsRepository(fileLocation);
        StudentProgressAnalyser analyser = new StudentProgressAnalyser(repo);
        StudentReport report = new StudentReport(output, analyser, reportType);
        report.run();
    }

}
