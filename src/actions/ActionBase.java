package actions;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import constants.ForwardConst;

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
            System.err.println(e.getCause());

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

}
