package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.MessageConst;
import constants.PropertyConst;
import constants.QueryConst;
import services.EmployeeService;

public class EmployeeAction extends ActionBase {
    private EmployeeService EmployeeService;
    @Override
    public void process() throws ServletException, IOException {
        if(checkWorker()) {
            EmployeeService = new EmployeeService();

            doCommand();

            EmployeeService.close();
        }
    }

    public void index() throws ServletException, IOException{
        int workStyle = toNumber(getRequestParam(AttributeConst.EMP_WORK_STYLE));
        int page = getPage();

        List<EmployeeView> employees = new ArrayList<>();
        long employeeCount;

        if(workStyle == Integer.MIN_VALUE) {
            //絞り込み表示の指定が無かった時
            employees = EmployeeService.getPerPage(page);
            employeeCount = EmployeeService.countAll();
        } else {
            //絞り込み表示の指定があった時
            employees = EmployeeService.getPerPage(page, workStyle);
            employeeCount = EmployeeService.countAll(workStyle);
        }

        putRequestScope(AttributeConst.EMPLOYEES, employees);
        putRequestScope(AttributeConst.EMP_COUNT, employeeCount);
        putRequestScope(AttributeConst.PAGE, page);
        putRequestScope(AttributeConst.MAX_ROW, QueryConst.ROW_PER_PAGE);

        String flush = getSessionScope(AttributeConst.FLUSH);
        if (flush != null) {
            putRequestScope(AttributeConst.FLUSH, flush);
            removeSessionScope(AttributeConst.FLUSH);
        }

        forward(ForwardConst.FW_EMP_INDEX);

    }

    public void entryNew() throws ServletException, IOException{
        putRequestScope(AttributeConst.TOKEN, getTokenId()); //CSRF対策用トークン
        putRequestScope(AttributeConst.EMPLOYEE, new EmployeeView()); //空の従業員インスタンス


        forward(ForwardConst.FW_EMP_NEW);
    }

    public void create() throws ServletException, IOException{
        if(checkToken()) {

            //入力内容をセット
            EmployeeView ev = new EmployeeView(
                    null,
                    getRequestParam(AttributeConst.EMP_NAME),
                    toNumber(getRequestParam(AttributeConst.EMP_SUBJECT)),
                    toNumber(getRequestParam(AttributeConst.EMP_WORK_STYLE)),
                    getRequestParam(AttributeConst.EMP_MAIL),
                    getRequestParam(AttributeConst.EMP_PASS),
                    AttributeConst.DEL_FALSE.getIntegerValue(),
                    null,
                    null
                    );

            String pepper = getContextScope(PropertyConst.PEPPER);

            List<String> errors = EmployeeService.create(ev, pepper);

            if(errors.size() > 0) {
                //バリデーションに引っかかった場合
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.EMPLOYEE, ev);
                putRequestScope(AttributeConst.ERR, errors);

                forward(ForwardConst.FW_EMP_NEW);
            } else {
                putSessionScope(AttributeConst.FLUSH, MessageConst.I_REGISTERED.getMessage());
                redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
            }



        }
    }

    public void show() throws ServletException, IOException{
        //IDパラメータから講師のインスタンスを取得
        EmployeeView ev = EmployeeService.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));

        if(ev == null || ev.getDeleteFlag() == AttributeConst.DEL_TRUE.getIntegerValue()) {
            //選択された講師が見つからなかった場合
            forward(ForwardConst.FW_ERR_UNK);
            return;
        }

        putRequestScope(AttributeConst.EMPLOYEE, ev);
        forward(ForwardConst.FW_EMP_SHOW);
    }

    public void edit() throws ServletException, IOException{
      //IDパラメータから講師のインスタンスを取得
        EmployeeView ev = EmployeeService.findOne(toNumber(getRequestParam(AttributeConst.EMP_ID)));

        if(ev == null || ev.getDeleteFlag() == AttributeConst.DEL_TRUE.getIntegerValue()) {
            //選択された講師が見つからなかった場合
            forward(ForwardConst.FW_ERR_UNK);
            return;
        }

        putRequestScope(AttributeConst.TOKEN, getTokenId());
        putRequestScope(AttributeConst.EMPLOYEE, ev);

        forward(ForwardConst.FW_EMP_EDIT);
    }

    public void update() throws ServletException, IOException{
        if(checkToken()) {

            EmployeeView ev = new EmployeeView(
                    toNumber(getRequestParam(AttributeConst.EMP_ID)),
                    getRequestParam(AttributeConst.EMP_NAME),
                    toNumber(getRequestParam(AttributeConst.EMP_SUBJECT)),
                    toNumber(getRequestParam(AttributeConst.EMP_WORK_STYLE)),
                    getRequestParam(AttributeConst.EMP_MAIL),
                    getRequestParam(AttributeConst.EMP_PASS),
                    AttributeConst.DEL_FALSE.getIntegerValue(),
                    null,
                    null
                    );

            String pepper = getContextScope(PropertyConst.PEPPER);
            List<String> errors = EmployeeService.update(ev, pepper);

            if(errors.size() > 0) {
                //入力内容にエラーがあった場合
                putRequestScope(AttributeConst.TOKEN, getTokenId());
                putRequestScope(AttributeConst.ERR, errors);
                putRequestScope(AttributeConst.EMPLOYEE, ev);

                forward(ForwardConst.FW_EMP_EDIT);
            } else {
                putRequestScope(AttributeConst.FLUSH, MessageConst.I_UPDATED.getMessage());
                redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
            }
        }
    }

    public void destroy() throws ServletException, IOException{
        if(checkToken()) {
            EmployeeService.detroy(toNumber(getRequestParam(AttributeConst.EMP_ID)));
            putSessionScope(AttributeConst.FLUSH, MessageConst.I_DESTROID.getMessage());
            redirect(ForwardConst.ACT_EMP, ForwardConst.CMD_INDEX);
        }
    }

    /**
     * 常勤講師かどうかチェックする
     * @return 常勤の時はtrue,非常勤時はfalseかつエラー画面遷移
     * @throws ServletException
     * @throws IOException
     */
    private boolean checkWorker() throws ServletException, IOException {
        EmployeeView ev = (EmployeeView)getSessionScope(AttributeConst.LOGIN_EMP);

        if(ev.getWorkStyle() == AttributeConst.FULL_TIME.getIntegerValue()) {
            return true;
        } else {
            forward(ForwardConst.FW_ERR_UNK);
            return false;
        }
    }


}
