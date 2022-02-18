package finalExam;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ApplicationRunner {
    public String run(String... arguments) {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(byteStream);
        StudentReportRunner.run(output, arguments);
        try {
            return byteStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }
    }
}
