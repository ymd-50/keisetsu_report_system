package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.QueryConst;
import constants.TableConst;
import services.ReportService;

public class ReportAction extends ActionBase {
    private ReportService reportService;

    @Override
    public void process() throws ServletException, IOException {
        reportService = new ReportService();
        doCommand();
        reportService.close();
    }

    public void index() throws ServletException, IOException {
        int page = getPage();
        int lessonStyle = toNumber(getRequestParam(AttributeConst.REP_LESSON_STYLE));
        int orderBy = toNumber(getRequestParam(AttributeConst.REP_ORDER_BY));

        EmployeeView ev = getSessionScope(AttributeConst.LOGIN_EMP);

        List<ReportView> reports;
        long reportsCount;
        if(ev.getWorkStyle() == TableConst.EMP_FULL_TIME) {
            //常勤講師の場合
            reports = reportService.getPerPage(page, lessonStyle, orderBy);
            reportsCount = reportService.countAll(lessonStyle, orderBy);
        } else {
            //非常勤講師の場合
            reports = reportService.getPerPage(page, lessonStyle, orderBy, ev);
            reportsCount = reportService.countAll(lessonStyle, orderBy, ev);
        }

        putRequestScope(AttributeConst.REPORTS, reports);
        putRequestScope(AttributeConst.REP_COUNT, reportsCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, QueryConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_REP_INDEX);

    }

    public void entryNew() throws ServletException, IOException {
        int lessonStyle = toNumber(getRequestParam(AttributeConst.REP_PARAM_LESSON_STYLE));

        ReportView rv = new ReportView();

        rv.setReportDate(LocalDate.now());
        rv.setLessonStyle(lessonStyle);

        putRequestScope(AttributeConst.TOKEN, getTokenId());
        putRequestScope(AttributeConst.REPORT, rv);



        forward(ForwardConst.FW_REP_NEW);
    }

    public void create() throws ServletException, IOException {
        if(checkToken()) {
            EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

            ReportView rv = new ReportView(
                    null,
                    ev,
                    toNumber(getRequestParam(AttributeConst.REP_LESSON_STYLE)),
                    getReportDate(),
                    toNumber(getRequestParam(AttributeConst.REP_CLASS_NUM)),
                    getClassName(),
                    toNumber(getRequestParam(AttributeConst.REP_GRADE)),
                    getRequestParam(AttributeConst.REP_SUBJECT),
                    getRequestParam(AttributeConst.REP_TITLE),
                    getRequestParam(AttributeConst.REP_CONTENT),
                    getRequestParam(AttributeConst.REP_ABSENTEE),
                    null,
                    null,
                    TableConst.REP_UNREAD
                    );

            List<String> errors = reportService.create(rv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.REPORT, rv);
                putRequestScope(AttributeConst.ERR, errors);
                putRequestScope(AttributeConst.REP_TEMP_SCHOOL, getRequestParam(AttributeConst.REP_TEMP_SCHOOL));
                putRequestScope(AttributeConst.REP_TEMP_STUDENT, getRequestParam(AttributeConst.REP_TEMP_STUDENT));


                forward(ForwardConst.FW_REP_NEW);
            } else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());


                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void show() throws ServletException, IOException {
        System.out.println("showCommand!!");
        ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

        if(rv == null) {
            forward(ForwardConst.FW_ERR_UNK);
        } else {
            putRequestScope(AttributeConst.REPORT, rv);
            forward(ForwardConst.FW_REP_SHOW);
        }
    }

    public void edit() throws ServletException, IOException {

    }

    public void update() throws ServletException, IOException {

    }

    public void destroy() throws ServletException, IOException {

    }

    private LocalDate getReportDate() {
        LocalDate day;
        if (getRequestParam(AttributeConst.REP_DATE) == null
                || getRequestParam(AttributeConst.REP_DATE).equals("")) {
            day = LocalDate.now();
        } else {
            day = LocalDate.parse(getRequestParam(AttributeConst.REP_DATE));
        }
        return day;
    }

    private String getClassName() {
        int lessonStyle = toNumber(getRequestParam(AttributeConst.REP_LESSON_STYLE));

        if(lessonStyle == TableConst.REP_GROUP) {
            //授業報告の場合
            return getRequestParam(AttributeConst.REP_CLASS_NAME);
        } else {
            //授業報告の場合
            String school = getRequestParam(AttributeConst.REP_TEMP_SCHOOL);
            String student = getRequestParam(AttributeConst.REP_TEMP_STUDENT);
            if(school == null || school.equals("") || student == null || student.equals("")) {
                //studentまたはschoolが空白の場合
                return "ERROR";
            }

            return school + "/" + student;
        }
    }

}
