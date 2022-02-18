package finalExam;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.stream.Collectors;

public class StudentProgressAnalyser {

    private final StudentsRepository repository;

    public StudentProgressAnalyser(StudentsRepository repository) {
        this.repository = repository;
    }

    public Map<Student, Long> countSpentHours() {
        return repository.loadStudents()
                .stream()
                .collect(Collectors.toMap(p -> p,
                        t -> ChronoUnit.HOURS.between(t.getStartDate(), LocalDateTime.now())
                                - t.getCurriculum().getCurriculumDuration()));
    }

}
