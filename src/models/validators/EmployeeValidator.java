package models.validators;

import java.util.ArrayList;
import java.util.List;

import actions.views.EmployeeView;
import constants.MessageConst;
import constants.NormConst;
import services.EmployeeService;

public class EmployeeValidator {
    /**
     * 講師の入力内容をバリデーション
     * @param service
     * @param ev
     * @param mailAddressDuplicateCheckFlag
     * @param passwordCheckFlag
     * @return エラーリスト
     */
    public static List<String> validate(
            EmployeeService service,
            EmployeeView ev,
            Boolean mailAddressDuplicateCheckFlag,
            Boolean passwordCheckFlag
            ){
        List<String> errors = new ArrayList<>();

        String mailAddressError = validateMailAddress(service, ev.getMailAddress(), mailAddressDuplicateCheckFlag);
        if(!mailAddressError.equals("")) {
            errors.add(mailAddressError);
        }

        String nameError = validateName(ev.getName());
        if(!nameError.equals("")) {
            errors.add(nameError);
        }

        String passwordError = validatePassword(ev.getPassword(), passwordCheckFlag);
        if(!passwordError.equals("")) {
            errors.add(passwordError);
        }

        return errors;
    }
    /**
     * メールアドレスをバリデーション
     * @param service
     * @param mailAddress
     * @param mailAddressDuplicateCheckFlag
     * @return エラーメッセージ
     */
    private static String validateMailAddress(EmployeeService service, String mailAddress, Boolean mailAddressDuplicateCheckFlag) {
        if(mailAddress == null || mailAddress.equals("")) {
            //空白
            return MessageConst.E_NOMAILADDRESS.getMessage();
        } else if( !checkMailAddress(mailAddress)) {
            //メールアドレスではない時
            return MessageConst.E_ISNT_MAILADDRES.getMessage();
        }

        if(mailAddressDuplicateCheckFlag) {
            long employeeCount = isDuplicateEmployee(service, mailAddress);
            if(employeeCount > 0) {
                //メールアドレスが既に登録済み
                return MessageConst.E_MAILADDRESS_EXIST.getMessage();
            }
        }

        return "";
    }

    /**
     * メールアドレスであるかを判定する
     * @param mailAddress
     * @return boolean値
     */
    private static Boolean checkMailAddress(String mailAddress) {
        if(mailAddress.matches(NormConst.MAIL_PATTERN.getValue())) {
            return true;
        }
        return false;
    }
    /**
     * 既に登録されているメールアドレスの件数を返す
     * @param service
     * @param mailAddress
     * @return 件数
     */
    private static long isDuplicateEmployee(EmployeeService service, String mailAddress) {
        long employeeCount = service.countByMailAddress(mailAddress);
        return employeeCount;
    }

    /**
     * 名前のバリデーション
     * @param name
     * @return エラーメッセージ
     */
    private static String validateName(String name) {
        if(name == null || name.equals("")) {
            //空白
            return MessageConst.E_NONAME.getMessage();
        }
        return "";
    }

    /**
     * パスワードのバリデーション
     * @param password
     * @param passwordCheckFlag
     * @return エラーメッセージ
     */
    private static String validatePassword(String password, Boolean passwordCheckFlag) {
        if(passwordCheckFlag) {
            if(password == null || password.equals("")) {
                //空白
                return MessageConst.E_NOPASSWORD.getMessage();
            } else if(password.length() < NormConst.PASS_MUST_OVER.getIntegerValue()) {
                //８文字未満
                return MessageConst.E_ISNT_OVER.getMessage();
            }
        }

        return "";
    }
}
