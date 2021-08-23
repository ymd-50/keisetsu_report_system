package actions;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.QueryConst;
import constants.TableConst;
import services.CommentService;
import services.ReportService;

public class ReportAction extends ActionBase {
    private ReportService reportService;
    private CommentService commentService;

    @Override
    public void process() throws ServletException, IOException {
        reportService = new ReportService();
        commentService = new CommentService();
        doCommand();
        commentService.close();
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
        ReportView rv;
        if(getSessionScope(AttributeConst.SE_REP_ID) == null) {
            //index.jspからのアクセス
            rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        } else {
            //CommentAction.create()からのリダイレクト
            rv = reportService.findOne(getSessionScope(AttributeConst.SE_REP_ID));
        }
        removeSessionScope(AttributeConst.SE_REP_ID);

        if(rv == null) {
            forward(ForwardConst.FW_ERR_UNK);
        } else {
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            if(ev.getId() != rv.getEmployee().getId()) {
                //投稿者以外の人が閲覧した場合既読にする
                reportService.checkRead(rv);
            }

            putRequestScope(AttributeConst.REPORT, rv);
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.COMMENT, new CommentView());

            //コメントを送る
            List<CommentView> comments = commentService.getComments(rv);
            putRequestScope(AttributeConst.COMMENTS, comments);

            String flush = getSessionScope(AttributeConst.FLUSH);
            if (flush != null) {
                putRequestScope(AttributeConst.FLUSH, flush);
                removeSessionScope(AttributeConst.FLUSH);
            }

            forward(ForwardConst.FW_REP_SHOW);
        }
    }

    public void edit() throws ServletException, IOException {
        ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if(rv == null || ev.getId() != rv.getEmployee().getId()) {
            forward(ForwardConst.FW_ERR_UNK);
        } else {
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.REPORT, rv);

            if(rv.getLessonStyle() == TableConst.REP_PERSONAL) {
                String school = separateSrash(0, rv.getClassName());
                String student = separateSrash(1, rv.getClassName());

                putRequestScope(AttributeConst.REP_TEMP_SCHOOL, school);
                putRequestScope(AttributeConst.REP_TEMP_STUDENT, student);
            }

            forward(ForwardConst.FW_REP_EDIT);

        }
    }

    public void update() throws ServletException, IOException {
        if(checkToken()) {
            ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));

            rv.setReportDate(getReportDate());
            rv.setClassNumber(toNumber(getRequestParam(AttributeConst.REP_CLASS_NUM)));
            rv.setGrade(toNumber(getRequestParam(AttributeConst.REP_GRADE)));
            rv.setClassName(getClassName());
            rv.setSubject(getRequestParam(AttributeConst.REP_SUBJECT));
            rv.setTitle(getRequestParam(AttributeConst.REP_TITLE));
            rv.setContent(getRequestParam(AttributeConst.REP_CONTENT));
            rv.setAbsentee(getRequestParam(AttributeConst.REP_ABSENTEE));

            List<String> errors = reportService.update(rv);

            if(errors.size() > 0) {
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.REPORT, rv);
                putRequestScope(AttributeConst.REP_TEMP_SCHOOL, getRequestParam(AttributeConst.REP_TEMP_SCHOOL));
                putRequestScope(AttributeConst.REP_TEMP_STUDENT, getRequestParam(AttributeConst.REP_TEMP_STUDENT));
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_REP_EDIT);

            } else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());

                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }
        }
    }


    public void destroy() throws ServletException, IOException {
        if(checkToken()) {
            ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

            if(rv == null || ev.getId() != rv.getEmployee().getId()) {
                forward(ForwardConst.FW_ERR_UNK);
            } else {
                reportService.destroy(rv);
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_DESTROID.getMessage());
                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
            }
        }

    }

    public void unread() throws ServletException, IOException {
        ReportView rv = reportService.findOne(toNumber(getRequestParam(AttributeConst.REP_ID)));
        EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);

        if(rv == null || ev.getId() == rv.getEmployee().getId()) {
            forward(ForwardConst.FW_ERR_UNK);
        } else {
            reportService.checkUnread(rv);
            redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
        }
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

    private String separateSrash(int key, String body) {
        String[] element = body.split("/");
        return element[key];
    }

}
