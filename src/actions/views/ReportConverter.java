package actions.views;

import java.util.ArrayList;
import java.util.List;

import models.Report;

public class ReportConverter {
    public static Report toModel(ReportView rv) {
        return new Report(
                rv.getId(),
                EmployeeConverter.toModel(rv.getEmployee()),
                rv.getLessonStyle(),
                rv.getReportDate(),
                rv.getClassNumber(),
                rv.getClassName(),
                rv.getGrade(),
                rv.getSubject(),
                rv.getTitle(),
                rv.getContent(),
                rv.getAbsentee(),
                rv.getCreatedAt(),
                rv.getUpdatedAt(),
                rv.getReadFlag()
                );
    }

    public static ReportView toView(Report r) {
        if(r == null) {
            return null;
        }

        return new ReportView(
                r.getId(),
                EmployeeConverter.toView(r.getEmployee()),
                r.getLessonStyle(),
                r.getReportDate(),
                r.getClassNumber(),
                r.getClassName(),
                r.getGrade(),
                r.getSubject(),
                r.getTitle(),
                r.getContent(),
                r.getAbsentee(),
                r.getCreatedAt(),
                r.getUpdatedAt(),
                r.getReadFlag()
                );
    }

    public static List<ReportView> toViewList(List<Report> rl){
        List<ReportView> rvl = new ArrayList<>();

        for(Report r : rl) {
            rvl.add(toView(r));
        }

        return rvl;
    }

    public static void copyViewtoModel(Report r, ReportView rv) {
        r.setId(rv.getId());
        r.setEmployee(EmployeeConverter.toModel(rv.getEmployee()));
        r.setLessonStyle(rv.getLessonStyle());
        r.setReportDate(rv.getReportDate());
        r.setClassNumber(rv.getClassNumber());
        r.setClassName(rv.getClassName());
        r.setGrade(rv.getGrade());
        r.setSubject(rv.getSubject());
        r.setTitle(rv.getTitle());
        r.setContent(rv.getContent());
        r.setAbsentee(rv.getAbsentee());
        r.setCreatedAt(rv.getCreatedAt());
        r.setUpdatedAt(rv.getUpdatedAt());
        r.setReadFlag(rv.getReadFlag());
    }

    public static void copyModelToView(Report r, ReportView rv) {
        rv.setId(r.getId());
        rv.setEmployee(EmployeeConverter.toView(r.getEmployee()));
        rv.setLessonStyle(r.getLessonStyle());
        rv.setReportDate(r.getReportDate());
        rv.setClassNumber(r.getClassNumber());
        rv.setClassName(r.getClassName());
        rv.setGrade(r.getGrade());
        rv.setSubject(r.getSubject());
        rv.setTitle(r.getTitle());
        rv.setContent(r.getContent());
        rv.setAbsentee(r.getAbsentee());
        rv.setCreatedAt(r.getCreatedAt());
        rv.setUpdatedAt(r.getUpdatedAt());
        rv.setReadFlag(r.getReadFlag());
    }
}
