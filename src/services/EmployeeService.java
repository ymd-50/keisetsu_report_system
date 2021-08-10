package services;

import constants.QueryConst;

public class EmployeeService extends ServiceBase {
    /**
     * メールアドレスパラメータと一致する講師件数を取得
     * @param mailAddress
     * @return
     */
    public long countByMailAddress(String mailAddress) {

        long employeeCount = (long) em.createNamedQuery(QueryConst.EMP_COUNT_BY_MAIL, Long.class)
                .setParameter(QueryConst.PARAM_MAIL, mailAddress)
                .getSingleResult();
        return employeeCount;
    }

}
