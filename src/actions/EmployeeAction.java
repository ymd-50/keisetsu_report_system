package actions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import actions.views.EmployeeView;
import constants.AttributeConst;
import constants.ForwardConst;
import constants.QueryConst;
import services.EmployeeService;

public class EmployeeAction extends ActionBase {
    private EmployeeService EmployeeService;
    @Override
    public void process() throws ServletException, IOException {
        EmployeeService = new EmployeeService();

        doCommand();

        EmployeeService.close();
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

    }

    public void show() throws ServletException, IOException{

    }

    public void edit() throws ServletException, IOException{

    }

    public void update() throws ServletException, IOException{

    }

    public void destroy() throws ServletException, IOException{

    }

}
