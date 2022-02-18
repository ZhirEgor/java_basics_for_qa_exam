package finalExam;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

import static java.time.DayOfWeek.*;

public class StudentProgressAnalyser {

    private final StudentsRepository repository;

    public StudentProgressAnalyser(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Student, Long> countSpentHours() {
        return repository.loadStudents()
                .stream()
                .collect(Collectors.toMap(p -> p,
                        t -> countDifferenceBetweenFinishDateAndCurrentTime(t.getStartDate()) - t.getCurriculum().getCurriculumDuration()));
    }

    private Long countDifferenceBetweenFinishDateAndCurrentTime(LocalDateTime startDate) {
        LocalDateTime currentDate = normalizeLocalDateTime(LocalDateTime.now());
        startDate = normalizeLocalDateTime(startDate);
        long hoursAmend = 0;
        if (currentDate.getHour() > startDate.getHour()) {
            hoursAmend = currentDate.getHour() - startDate.getHour();
        }
        if (currentDate.getHour() < startDate.getHour()) {
            hoursAmend = currentDate.getHour() - startDate.getHour() + 8;
        }
        return countWeekDaysMath(startDate, currentDate) * 8 + hoursAmend;
    }

    private long countWeekDaysMath(LocalDateTime start, LocalDateTime stop) {
        long count = 0;
        final DayOfWeek startW = start.getDayOfWeek();
        final DayOfWeek stopW = stop.getDayOfWeek();

        final long days = ChronoUnit.DAYS.between(start, stop);
        final long daysWithoutWeekends = days - 2 * ((days + startW.getValue()) / 7);

        //adjust for starting and ending on a Sunday:
        count = daysWithoutWeekends + (startW == DayOfWeek.SUNDAY ? 1 : 0) + (stopW == DayOfWeek.SUNDAY ? 1 : 0);

        return count;
    }

    private LocalDateTime normalizeLocalDateTime(LocalDateTime localDateTime) {
        if (localDateTime.getHour() < 10) {
            localDateTime = localDateTime.plusHours(10 - localDateTime.getHour());
        }
        if (localDateTime.getHour() > 18) {
            localDateTime = localDateTime.plusDays(1);
            localDateTime = localDateTime.minusHours(localDateTime.getHour() - 10);
        }
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        if (dayOfWeek.equals(SATURDAY)) {
            localDateTime = localDateTime.plusDays(2);
        }
        if (dayOfWeek.equals(SUNDAY)) {
            localDateTime = localDateTime.plusDays(1);
        }
        return localDateTime;
    }

}
