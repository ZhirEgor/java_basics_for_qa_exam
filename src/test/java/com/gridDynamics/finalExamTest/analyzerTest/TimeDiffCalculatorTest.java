package com.gridDynamics.finalExamTest.analyzerTest;

import com.gridDynamics.finalExam.reportAnalyser.TimeDiffCalculator;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

public class TimeDiffCalculatorTest {

    @ParameterizedTest(name = "Validate that correct working hours time difference counted between two dates")
    @MethodSource("getTimeDifferenceValidationTestData")
    public void validateTimeDifferenceBetweenTwoDates(String startDate, String finishDate, int timeDifference) {
        TimeDiffCalculator analyser = new TimeDiffCalculator();
        SoftAssertions softAssertions = new SoftAssertions();

        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse(startDate), LocalDateTime.parse(finishDate)).intValue())
                .as(String.format("Amount of working hours between start date = %s and finish date = %s equals %s hours", startDate, finishDate, timeDifference))
                .isEqualTo(timeDifference);

    }

    protected static Stream<Arguments> getTimeDifferenceValidationTestData() {
        return Stream.of(
                Arguments.of("2022-02-14T10:00:00", "2022-02-14T18:00:00", 8),
                Arguments.of("2022-02-14T10:00:00", "2022-02-18T18:00:00", 40),
                Arguments.of("2022-02-14T02:00:00", "2022-02-14T23:00:00", 8),
                Arguments.of("2022-02-14T23:00:00", "2022-02-18T02:00:00", 24),
                Arguments.of("2022-02-14T14:00:00", "2022-02-18T12:00:00", 32),
                Arguments.of("2022-02-12T10:00:00", "2022-02-20T18:00:00", 40)
        );
    }

}
