package actions.views;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReportView {
    private Integer id;

    private EmployeeView employee;

    private Integer lessonStyle;

    private LocalDate reportDate;

    private Integer classNumber;

    private String className;

    private Integer grade;

    private String subject;

    private String title;

    private Integer content;

    private String absentee;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer readFlag;
}
