package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.CommentView;
import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.TableConst;
import services.CommentService;
import services.ReportService;

public class CommentAction extends ActionBase {
    private CommentService commentService;
    private ReportService reportService;

    @Override
    public void process() throws ServletException, IOException {
        commentService = new CommentService();
        reportService = new ReportService();
        doCommand();
        reportService.close();
        commentService.close();
    }

    public void create() throws ServletException, IOException{
        if(checkToken()) {
            EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);
            int reportId = toNumber(getRequestParam(AttributeConst.REP_ID));

            CommentView cv = new CommentView(
                    null,
                    ev,
                    reportService.findOne(reportId),
                    getRequestParam(AttributeConst.COM_CONTENT),
                    null,
                    null,
                    TableConst.COM_UNREAD
                    );

            String error = commentService.create(cv);

            if(error != null) {
                putSessionScope(AttributeConst.ERR, error);
                putSessionScope(AttributeConst.SE_REP_ID, reportId);

                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW);
            } else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_COMMENTED.getMessage());
                putSessionScope(AttributeConst.SE_REP_ID, reportId);

                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW);
            }
        }
    }

    public void destroy() throws ServletException, IOException{
        if(checkToken()) {
            EmployeeView ev = (EmployeeView) getSessionScope(AttributeConst.LOGIN_EMP);
            CommentView cv = commentService.findOne(toNumber(getRequestParam(AttributeConst.COM_ID)));

            if(cv == null || cv.getEmployee().getId() != ev.getId()) {
                forward(ForwardConst.FW_ERR_UNK);
            } else {
                commentService.destroy(cv);

                putSessionScope(AttributeConst.FLUSH, MessageConst.I_DESTROID.getMessage());
                putSessionScope(AttributeConst.SE_REP_ID, cv.getReport().getId());

                redirect(ForwardConst.ACT_REP, ForwardConst.CMD_SHOW);
            }
        }
    }

}
