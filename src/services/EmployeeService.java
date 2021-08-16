package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

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

    /**
     * mailAddress,plainPassパラメータから講師を一人取得する
     * @param mailAddress
     * @param plainPass
     * @param pepper
     * @return  講師のEmployeeViewインスタンス
     */
    public EmployeeView findOne(String mailAddress, String plainPass, String pepper) {
        Employee e = null;
        try {
            String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

            e = em.createNamedQuery(QueryConst.EMP_GET_BY_MAIL_AND_PASS, Employee.class)
                    .setParameter(QueryConst.PARAM_MAIL, mailAddress)
                    .setParameter(QueryConst.PARAM_PASS, pass)
                    .getSingleResult();
        } catch (NoResultException ex) {
        }
        return EmployeeConverter.toView(e);
    }

    /**
     * idパラメータから講師を一人取得する
     * @param id
     * @return 講師のEmployeeViewインスタンス
     */
    public EmployeeView findOne(int id) {
        Employee e = findOneInternal(id);
        return EmployeeConverter.toView(e);
    }

    /**
     * idパラメータから講師を一人取得する
     * @param id
     * @return 講師のEmployeeインスタンス
     */
    private Employee findOneInternal(int id) {
        Employee e = em.find(Employee.class, id);
        return e;
    }

    /**
     * 講師を更新する
     * @param ev
     * @param pepper
     * @return errorList
     */
    public List<String> update(EmployeeView ev, String pepper) {
        //編集前のEmployeeView
        EmployeeView saveEmp = findOne(ev.getId());

        //validateFlagの処理
        boolean validateMail = false;
        if(!ev.getMailAddress().equals(saveEmp.getMailAddress())) {
            validateMail = true;
            saveEmp.setMailAddress(ev.getMailAddress());
        }
        //validateFlagの処理
        boolean validatePass = false;
        if(ev.getPassword() != null && !ev.getPassword().equals("")) {
            validatePass = true;
            saveEmp.setPassword(ev.getPassword());
        }

        saveEmp.setName(ev.getName());
        saveEmp.setSubject(ev.getSubject());
        saveEmp.setWorkStyle(ev.getWorkStyle());

        LocalDateTime now = LocalDateTime.now();
        saveEmp.setUpdatedAt(now);

        List<String> errors = EmployeeValidator.validate(this, saveEmp, validateMail, validatePass);

        //ハッシュ化したパスワードをセット
        saveEmp.setPassword(EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper));

        if(errors.size() == 0) {
            //エラーがない場合はupdate
            update(saveEmp);
        }

        return errors;
    }

    /**
     * DBに講師のデータを更新する
     * @param ev
     */
    private void update(EmployeeView ev) {
        em.getTransaction().begin();
        Employee e = findOneInternal(ev.getId());
        EmployeeConverter.copyViewToModel(e, ev);
        em.getTransaction().commit();
    }



}
