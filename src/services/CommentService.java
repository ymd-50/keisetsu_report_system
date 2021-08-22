package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.CommentConverter;
import actions.views.CommentView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.QueryConst;
import models.Comment;
import models.validators.CommentValidator;

public class CommentService extends ServiceBase {

    public String create(CommentView cv) {
        String error = CommentValidator.validate(cv);
        if(error == null) {
            LocalDateTime now = LocalDateTime.now();
            cv.setCreatedAt(now);
            cv.setUpdatedAt(now);
            createInternal(cv);
        }
        return error;
    }

    private void createInternal(CommentView cv) {
        em.getTransaction().begin();
        em.persist(CommentConverter.toModel(cv));
        em.getTransaction().commit();
    }

    public List<CommentView> getComments(ReportView rv) {
        List<Comment> comments = em.createNamedQuery(QueryConst.COM_GET_BY_REP_ID ,Comment.class)
                .setParameter(QueryConst.PARAM_REP, ReportConverter.toModel(rv))
                .getResultList();
        return CommentConverter.toViewList(comments);
    }

}
