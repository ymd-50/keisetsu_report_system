package actions;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import actions.views.ReportView;
import constants.AttributeConst;
import constants.ForwardConst;
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

    }

    public void create() throws ServletException, IOException {

    }

    public void show() throws ServletException, IOException {

    }

    public void edit() throws ServletException, IOException {

    }

    public void update() throws ServletException, IOException {

    }

    public void destroy() throws ServletException, IOException {

    }



}
