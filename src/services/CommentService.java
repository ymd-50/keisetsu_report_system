package services;

import java.time.LocalDateTime;

import actions.views.CommentConverter;
import actions.views.CommentView;
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

}
