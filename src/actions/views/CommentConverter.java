package actions.views;

import models.Comment;

public class CommentConverter {

    public static Comment toModel(CommentView cv) {
        return new Comment(
                cv.getId(),
                EmployeeConverter.toModel(cv.getEmployee()),
                ReportConverter.toModel(cv.getReport()),
                cv.getContent(),
                cv.getCreatedAt(),
                cv.getUpdatedAt(),
                cv.getReadFlag()
                );
    }

}
