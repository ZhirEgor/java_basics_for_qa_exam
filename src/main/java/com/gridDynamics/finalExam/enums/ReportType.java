package com.gridDynamics.finalExam.enums;

public enum ReportType {

    SHORT,
    LONG;

    public static ReportType getReportType(int reportType) {
        if (reportType == 1) {
            return LONG;
        } else {
            return SHORT;
        }
    }

}
