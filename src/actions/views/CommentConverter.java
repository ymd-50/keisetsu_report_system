package actions.views;

import java.util.ArrayList;
import java.util.List;

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

    public static CommentView toView(Comment c) {
        return new CommentView(
                c.getId(),
                EmployeeConverter.toView(c.getEmployee()),
                ReportConverter.toView(c.getReport()),
                c.getContent(),
                c.getCreatedAt(),
                c.getUpdatedAt(),
                c.getReadFlag()
                );
    }

    public static List<CommentView> toViewList(List<Comment> comments) {
        List<CommentView> cvl = new ArrayList<>();

        for(Comment c : comments) {
            cvl.add(toView(c));
        }

        return cvl;
    }

}
