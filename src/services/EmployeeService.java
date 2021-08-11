package services;

import java.time.LocalDateTime;
import java.util.List;

import actions.views.EmployeeConverter;
import actions.views.EmployeeView;
import constants.QueryConst;
import models.Employee;
import models.validators.EmployeeValidator;
import utils.EncryptUtil;

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

    /**
     * pageパラメータから15件の講師リストを取得
     * @param page
     * @return 講師リスト
     */
    public List<EmployeeView> getPerPage(int page) {
        List<Employee> employees = em.createNamedQuery(QueryConst.EMP_GET_ALL, Employee.class)
                .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(QueryConst.ROW_PER_PAGE)
                .getResultList();

        return EmployeeConverter.toViewList(employees);
    }


    /**
     * page,workStyleパラメータから15件の講師リストを取得
     * @param page
     * @param workStyle
     * @return 講師リスト
     */
    public List<EmployeeView> getPerPage(int page, int workStyle){
        List<Employee> employees = em.createNamedQuery(QueryConst.EMP_GET_BY_WORK_STYLE, Employee.class)
                .setParameter(QueryConst.PARAM_WORK_STYLE, workStyle)
                .setFirstResult(QueryConst.ROW_PER_PAGE * (page - 1))
                .setMaxResults(QueryConst.ROW_PER_PAGE)
                .getResultList();
        return EmployeeConverter.toViewList(employees);
    }

    /**
     * 講師の件数を返す
     * @return 講師の件数
     */
    public long countAll() {
        long employeeCount = em.createNamedQuery(QueryConst.EMP_COUNT, Long.class)
                .getSingleResult();
        return employeeCount;
    }

    /**
     * workStyleパラメータから一致する講師の件数を返す
     * @param workStyle
     * @return 講師の件数
     */
    public long countAll(int workStyle) {
        long employeeCount = em.createNamedQuery(QueryConst.EMP_COUNT_BY_WORK_STYLE, Long.class)
                .setParameter(QueryConst.PARAM_WORK_STYLE, workStyle)
                .getSingleResult();
        return employeeCount;
    }

    /**
     * パスワードをハッシュ化した後に講師を一人登録する
     * @param ev
     * @param pepper
     * @return エラーリスト
     */
    public List<String> create(EmployeeView ev, String pepper){



        LocalDateTime now = LocalDateTime.now();
        ev.setCreatedAt(now);
        ev.setUpdatedAt(now);

        List<String> errors = EmployeeValidator.validate(this, ev, true, true);

        String pass = EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper);
        ev.setPassword(pass);

        if(errors.size() == 0) {
            create(ev);
        }

        return errors;
    }

    /**
     * 講師を一人登録
     * @param ev
     */
    private void create(EmployeeView ev) {
        em.getTransaction().begin();
        em.persist(EmployeeConverter.toModel(ev));
        em.getTransaction().commit();
    }



}
