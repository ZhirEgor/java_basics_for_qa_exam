package finalExamTest;

import finalExam.StudentProgressAnalyser;
import finalExam.StudentsRepository;
import org.assertj.core.api.SoftAssertions;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StudentProgressAnalyzerTest extends StudentBaseTest {

    @Test
    public void validatePositiveTimeDifferenceCorrectly() {
        StudentsRepository mockRepository = mock(StudentsRepository.class);
        when(mockRepository.loadStudents()).thenReturn(Arrays.asList(stepanovStepanP));

        StudentProgressAnalyser analyser = new StudentProgressAnalyser(mockRepository);

        analyser.countSpentHours().forEach((student, timeDifference) ->
                assertThat(timeDifference.intValue()).as(String.format("Time difference between current moment and %s should be positive", student.getStartDate())).isPositive());
    }

    @Test
    public void validateNegativeTimeDifferenceCorrectly() {
        StudentsRepository mockRepository = mock(StudentsRepository.class);
        when(mockRepository.loadStudents()).thenReturn(Arrays.asList(stepanovStepanF));

        StudentProgressAnalyser analyser = new StudentProgressAnalyser(mockRepository);

        analyser.countSpentHours().forEach((student, timeDifference) ->
                assertThat(timeDifference.intValue()).as(String.format("Time difference between current moment and %s should be negative", student.getStartDate())).isNegative());
    }

    @Test
    public void validateTimeDifferenceBetweenTwoDates() {
        StudentProgressAnalyser analyser = new StudentProgressAnalyser(mock(StudentsRepository.class));
        SoftAssertions softAssertions = new SoftAssertions();

        //regular day worktime - 8 hours
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-14T10:00:00"), LocalDateTime.parse("2022-02-14T18:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(40);

        //regular week worktime - 40 hours
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-14T10:00:00"), LocalDateTime.parse("2022-02-18T18:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(40);

        //validation of normalizing 2 hours - start and finish hours are set incorrectly, on non-working hours
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-14T02:00:00"), LocalDateTime.parse("2022-02-18T23:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(40);
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-14T23:00:00"), LocalDateTime.parse("2022-02-18T02:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(24);
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-14T14:00:00"), LocalDateTime.parse("2022-02-18T12:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(30);

        //validation of normalizing 2 dates - start and finish date are set incorrectly, on sunday and saturday
        softAssertions.assertThat(analyser.countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime.parse("2022-02-12T10:00:00"), LocalDateTime.parse("2022-02-20T18:00:00")).intValue())
                .as("Amount of working hours during the week equals 40 hours")
                .isEqualTo(40);
    }

}
