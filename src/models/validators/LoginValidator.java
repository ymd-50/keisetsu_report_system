package models.validators;

import actions.views.EmployeeView;
import constants.MessageConst;
import constants.TableConst;
import services.EmployeeService;

public class LoginValidator {
    /**
     * ログインの入力に対しバリデーションを行う
     * @param mailAddress
     * @param plainPass
     * @param pepper
     * @return エラーメッセージ（エラーがない場合は""を返す）
     */
    public static String validate(String mailAddress, String plainPass, String pepper){
        EmployeeService service = new EmployeeService();
        String error;

        if(mailAddress != null && !mailAddress.equals("")
                && plainPass != null && !plainPass.equals("")) {
            EmployeeView ev = service.findOne(mailAddress, plainPass, pepper);

            if(ev != null && ev.getDeleteFlag() == TableConst.EMP_DEL_FALSE) {
                //入力にエラーがない場合
                error = "";
            } else {
                //evがnullまたは削除済みの場合
                error = MessageConst.E_NOACCOUNT.getMessage();
            }
        } else {
            //未入力があった場合
            error = MessageConst.E_NOMAIL_OR_NOPASS.getMessage();
        }

        return error;
    }
}
