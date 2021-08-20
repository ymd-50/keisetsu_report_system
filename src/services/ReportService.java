package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import actions.views.ReportConverter;
import actions.views.ReportView;
import constants.QueryConst;
import models.Report;
import models.validators.ReportValidator;

public class ReportService extends ServiceBase {

    public List<ReportView> getPerPage(int page, int lessonStyle, int orderBy, EmployeeView ev) {
        List<Report> reports;
        if(lessonStyle == Integer.MIN_VALUE) {
            //lessonStyleの指定が無い時
            if(orderBy == QueryConst.ORDER_BY_REP_DATE) {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_EMP_ORDER_BY_REP_DATE, Report.class)
                        .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            } else {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_EMP_ORDER_BY_UPDATE, Report.class)
                        .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            }
        } else {
            //lessonStyleの指定がある時
            if(orderBy == QueryConst.ORDER_BY_REP_DATE) {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_LESSON_STYLE_AND_EMP_ORDER_BY_REP_DATE, Report.class)
                        .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                        .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            } else {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_LESSON_STYLE_AND_EMP_ORDER_BY_UPDATE, Report.class)
                        .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                        .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            }
        }

        return ReportConverter.toViewList(reports);
    }

    public List<ReportView> getPerPage(int page, int lessonStyle, int orderBy) {
        List<Report> reports;
        if(lessonStyle == Integer.MIN_VALUE) {
            //lessonStyleの指定が無い時
            if(orderBy == QueryConst.ORDER_BY_REP_DATE) {
                reports = em.createNamedQuery(QueryConst.REP_GET_ALL_ORDER_BY_REP_DATE, Report.class)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            } else {
                reports = em.createNamedQuery(QueryConst.REP_GET_ALL_ORDER_BY_UPDATE, Report.class)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            }
        } else {
            //lessonStyleの指定がある時
            if(orderBy == QueryConst.ORDER_BY_REP_DATE) {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_LESSON_STYLE_ORDER_BY_REP_DATE, Report.class)
                        .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            } else {
                reports = em.createNamedQuery(QueryConst.REP_GET_BY_LESSON_STYLE_ORDER_BY_UPDATE, Report.class)
                        .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                        .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                        .setMaxResults(QueryConst.ROW_PER_PAGE)
                        .getResultList();
            }
        }

        return ReportConverter.toViewList(reports);
    }

    public long countAll(int lessonStyle, int orderBy) {
        long count;
        if(lessonStyle == Integer.MIN_VALUE) {
            //lessonStyleの指定が無い時
            count = (long)em.createNamedQuery(QueryConst.REP_COUNT, Long.class)
                    .getFirstResult();
        } else {
            //lessonStyleの指定がある時
            count = (long)em.createNamedQuery(QueryConst.REP_COUNT_BY_LESSON_STYLE, Long.class)
                    .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                    .getFirstResult();
        }
        return count;
    }

    public long countAll(int lessonStyle, int orderBy, EmployeeView ev) {
        long count;
        if(lessonStyle == Integer.MIN_VALUE) {
            //lessonStyleの指定が無い時
            count = (long)em.createNamedQuery(QueryConst.REP_COUNT_BY_EMP, Long.class)
                    .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                    .getFirstResult();
        } else {
            //lessonStyleの指定がある時
            count = (long)em.createNamedQuery(QueryConst.REP_COUNT_BY_LESSON_STYLE_AND_EMP, Long.class)
                    .setParameter(QueryConst.PARAM_EMP, EmployeeConverter.toModel(ev))
                    .setParameter(QueryConst.PARAM_LESSON_STYLE, lessonStyle)
                    .getFirstResult();
        }
        return count;
    }

    public List<String> create(ReportView rv) {
        List<String> errors = ReportValidator.validate(rv);
        if (errors.size() == 0) {
            LocalDateTime now = LocalDateTime.now();
            rv.setCreatedAt(now);
            rv.setUpdatedAt(now);
            createInternal(rv);
        }
        return errors;
    }

    private void createInternal(ReportView rv) {
        em.getTransaction().begin();
        em.persist(ReportConverter.toModel(rv));
        em.getTransaction().commit();
    }

}
