package actions.views;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import models.Employee;
import models.Report;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentView {
    private Integer id;

    private Employee employee;

    private Report report;

    private String content;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Integer readFlag;
}
