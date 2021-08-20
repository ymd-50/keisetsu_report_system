package models.validators;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import actions.views.ReportView;
import constants.MessageConst;

public class ReportValidator {

    /**
     * 指導報告の内容に対しバリデーションを行う
     * @param rv
     * @return
     */
    public static List<String> validate(ReportView rv){
        List<String> errors = new ArrayList<>();

        String reportDateError = validateReportDate(rv.getReportDate());
        if(!reportDateError.equals("")) {
            errors.add(reportDateError);
        }

        String classNameError = validateClassName(rv.getClassName());
        if(!classNameError.equals("")) {
            errors.add(classNameError);
        }

        String subjectError = validateSubject(rv.getSubject());
        if(!subjectError.equals("")) {
            errors.add(subjectError);
        }

        String titleError = validateTitle(rv.getTitle());
        if(!titleError.equals("")) {
            errors.add(titleError);
        }

        String contentError = validateContent(rv.getContent());
        if(!contentError.equals("")) {
            errors.add(contentError);
        }

        String absenteeError = validateAbsentee(rv.getAbsentee());
        if(!absenteeError.equals("")) {
            errors.add(absenteeError);
        }

        return errors;
    }

    /**
     * 日時が未来の場合にエラー
     * @param date
     * @return エラー文
     */
    private static String validateReportDate(LocalDate date) {
        LocalDate now = LocalDate.now();
        if(now.isBefore(date) == true) {
            return MessageConst.E_REPORT_DATE.getMessage();
        }

        return "";
    }

    /**
     * 空の場合エラー
     * @param className
     * @return エラー文
     */
    private static String validateClassName(String className) {
        if(className == null || className.equals("")) {
            return MessageConst.E_NOCLASSNAME.getMessage();
        } else if(className.equals("ERROR")) {
            return MessageConst.E_NOSCHOOL_OR_NOSTYDENT.getMessage();
        }

        return "";
    }

    /**
     * 空の場合エラー
     * @param subject
     * @return エラー文
     */
    private static String validateSubject(String subject) {
        if(subject == null || subject.equals("")) {
            return MessageConst.E_NOSUBJECT.getMessage();
        }

        return "";
    }

    /**
     * 空の場合エラー
     * @param title
     * @return エラー文
     */
    private static String validateTitle(String title) {
        if(title == null || title.equals("")) {
            return MessageConst.E_NOTITLE.getMessage();
        }

        return "";
    }

    /**
     * 空の場合エラー
     * @param content
     * @return エラー文
     */
    private static String validateContent(String content) {
        if(content == null || content.equals("")) {
            return MessageConst.E_NOCONTENT.getMessage();
        }

        return "";
    }

    private static String validateAbsentee(String absentee) {
        if(absentee == null || absentee.equals("")) {
            return MessageConst.E_NOABSENTEE.getMessage();
        }

        return "";
    }


}
