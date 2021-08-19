package actions;

import java.io.IOException;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import constants.TableConst;
import models.validators.LoginValidator;
import services.EmployeeService;

public class AuthAction extends ActionBase {
    private EmployeeService service;

    @Override
    public void process() throws ServletException, IOException {
        service = new EmployeeService();

        doCommand();

        service.close();
    }

    public void showLogin() throws ServletException, IOException{
        putRequestScope(AttributeConst.TOKEN, getTokenId());

        String flush = getSessionScope(AttributeConst.FLUSH);
        if(flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_LOGIN);
    }

    public void login() throws ServletException, IOException{
        String mailAddress = getRequestParam(AttributeConst.EMP_MAIL);
        String plainPass = getRequestParam(AttributeConst.EMP_PASS);
        String pepper = getContextScope(PropertyConst.PEPPER);

        String error = LoginValidator.validate(mailAddress, plainPass, pepper);

        if(error.equals("")) {
            if(checkToken()) {
                //認証成功
                EmployeeView ev = service.findOne(mailAddress, plainPass, pepper);
                putSessionScope(AttributeConst.LOGIN_EMP, ev);
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_LOGINED.getMessage());

                if(ev.getWorkStyle() == TableConst.EMP_FULL_TIME) {
                    //常勤講師の場合
                    redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
                } else {
                    //非常勤講師の場合
                    redirect(ForwardConst.ACT_REP, ForwardConst.CMD_INDEX);
                }
            }
        } else {
            //認証失敗
            putRequestScope(AttributeConst.TOKEN, getTokenId());
            putRequestScope(AttributeConst.EMP_MAIL, mailAddress);
            putRequestScope(AttributeConst.ERR, error);

            forward(ForwardConst.FW_LOGIN);
        }
    }

}
