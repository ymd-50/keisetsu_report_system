package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.AttributeConst;
import constants.ForwardConst;
import constants.PropertyConst;

public abstract class ActionBase {

    protected ServletContext context;
    protected HttpServletRequest request;
    protected HttpServletResponse response;

    /**
     * 初期化処理
     * @param context
     * @param request
     * @param response
     */
    public void init(ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        this.context = context;
        this.request = request;
        this.response = response;
    }

    /**
     * FrontControllerから呼び出しされるメソッド
     * @throws ServletException
     * @throws IOException
     */
    public  abstract void process() throws ServletException, IOException;

    /**
     * リクエストパラメータから適当なcommandメソッドを実行
     * @throws ServletException
     * @throws IOException
     */
    protected void doCommand() throws ServletException, IOException{

        Method commandMethod;

        try {
            String command = request.getParameter(ForwardConst.CMD.getValue());

            commandMethod = this.getClass().getDeclaredMethod(command, new Class[0]);
            commandMethod.invoke(this, new Object[0]);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException
                | InvocationTargetException e) {
            System.err.print("doCommand->" + e.getCause());

            //例外時はエラー画面のjspファイルを呼び出す
            forward(ForwardConst.FW_ERR_UNK);
        }
    }

    /**
     * 指定されたjspファイルを呼び出す
     * @param target
     * @throws ServletException
     * @throws IOException
     */
    protected void forward(ForwardConst target) throws ServletException, IOException{
        String forward = "/WEB-INF/views/" + target.getValue() + ".jsp";
        RequestDispatcher rd = request.getRequestDispatcher(forward);

        rd.forward(request, response);
    }

    /**
     * パラメータから任意のURLへリダイレクトする
     * @param action
     * @param command
     * @throws ServletException
     * @throws IOException
     */
    protected void redirect(ForwardConst action, ForwardConst command) throws ServletException, IOException{

        String url = request.getContextPath() + "/?action=" + action.getValue();
        if(command != null) {
            url = url + "&command=" + command.getValue();
        }

        response.sendRedirect(url);
    }

    /**
     * CSRF対策 不正時はエラー画面呼び出し
     * @return 正当：true、不正：false
     * @throws ServletException
     * @throws IOException
     */
    protected Boolean checkToken() throws ServletException, IOException{

        String _token = getRequestParam(AttributeConst.TOKEN);

        if(_token == null || !(_token.equals(getTokenId()))) {
            //不正
            forward(ForwardConst.FW_ERR_UNK);
            return false;
        } else {
            //正当
            return true;
        }
    }

    /**
     * セッションID取得
     * @return セッションID
     */
    protected String getTokenId() {
        return request.getSession().getId();
    }

    /**
     * リクエストパラメータで指定されたページ数を返す
     * @return ページ数
     */
    protected int getPage() {
        int page;
        page = toNumber(request.getParameter(AttributeConst.PAGE.getValue()));
        if (page == Integer.MIN_VALUE) {
            page = 1;
        }
        return page;
    }

    /**
     * String値をInt値に変換し返す
     * FormatException時にはMIN_VALUEを返す
     * @param strNumber
     * @return 数値
     */
    protected int toNumber(String strNumber) {
        int number = 0;
        try {
            number = Integer.parseInt(strNumber);
        } catch (Exception e) {
            number = Integer.MIN_VALUE;
        }
        return number;
    }

    /**
     *
     * @param strDate
     * @return
     */
    protected LocalDate toLocalDate(String strDate) {
        if (strDate == null || strDate.equals("")) {
            return LocalDate.now();
        }
        return LocalDate.parse(strDate);
    }


    protected String getRequestParam(AttributeConst key) {
        return request.getParameter(key.getValue());
    }

    protected <V> void putRequestScope(AttributeConst key, V value) {
        request.setAttribute(key.getValue(), value);
    }


    @SuppressWarnings("unchecked")
    protected <R> R getSessionScope(AttributeConst key) {
        return (R) request.getSession().getAttribute(key.getValue());
    }


    protected <V> void putSessionScope(AttributeConst key, V value) {
        request.getSession().setAttribute(key.getValue(), value);
    }


    protected void removeSessionScope(AttributeConst key) {
        request.getSession().removeAttribute(key.getValue());
    }


    @SuppressWarnings("unchecked")
    protected <R> R getContextScope(PropertyConst key) {
        return (R) context.getAttribute(key.getValue());
    }

}
