package com.gridDynamics.finalExam.runners;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ApplicationRunner {

    public String run(int reportType, String... arguments) {

        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        PrintStream output = new PrintStream(byteStream);
        StudentReportRunner.run(output, reportType, arguments);
        try {
            return byteStream.toString("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "";
        }

    }
}
