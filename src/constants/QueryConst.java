package constants;

public interface QueryConst {
    //Entity名
    String ENTITY_EMP = "employee";
    String ENTITY_REP = "report";
    String ENTITY_COM = "comment";

    //パラメータ
    String PARAM_MAIL = "mailAddress";
    String PARAM_PASS = "password";
    String PARAM_EMP_ID = "employeeId";
    String PARAM_REP_ID = "reportId";

    /*
     * employeesテーブル
     */
    //全ての講師を取得（ID順）
    String EMP_GET_ALL = ENTITY_EMP + ".getAll";
    String EMP_GET_ALL_DEF = "select e from Employee as e order by e.id desc";
    //全ての講師の件数を取得
    String EMP_COUNT = ENTITY_EMP + ".count";
    String EMP_COUNT_DEF = "select count(e) from Employee as e";
    //非常勤講師のみ取得
    String EMP_GET_PART_TIME = ENTITY_EMP + ".getPartTime";
    String EMP_GET_PART_TIME_DEF = "select count(e) from Employee as e where e.workStyle = :" + TableConst.EMP_PART_TIME;
    //常勤講師のみ取得
    String EMP_GET_FULL_TIME = ENTITY_EMP + ".getFullTime";
    String EMP_GET_FULL_TIME_DEF = "select count(e) from Employee as e where e.workStyle = :" + TableConst.EMP_FULL_TIME;
    //mailAddressとpasswordのパラメータと一致する講師を取得
    String EMP_GET_BY_MAIL_AND_PASS = ENTITY_EMP + ".getByMailAndPass";
    String EMP_GET_BY_MAIL_AND_PASS_DEF = "select e from Employee as e where e.deleteFlag = 0 and e.mailAddress = :" + PARAM_MAIL + " and e.password = :" + PARAM_PASS;



    /*
     * reportsテーブル
     */
    //全ての指導報告を取得（指導日時順）
    String REP_GET_ALL_ORDER_BY_REP_DATE = ENTITY_REP + ".getAllOrderByReportDate";
    String REP_GET_ALL_ORDER_BY_REP_DATE_DEF = "select r from Report as r order by r.reportDate desc";
    //全ての指導報告を取得（最終更新日時順）
    String REP_GET_ALL_ORDER_BY_UPDATE = ENTITY_REP + "getAllOrderByUpdate";
    String REP_GET_ALL_ORDER_BY_UPDATE_DEF = "select r from Report as r order by r.updatedAt desc";
    //全ての指導報告の件数を取得
    String REP_COUNT = ENTITY_REP + ".count";
    String REP_COUNT_DEF = "select count(r) from Report as r";
    //employeeIdパラメータと一致する指導報告の件数を取得
    String REP_COUNT_BY_EMP_ID = ENTITY_REP + ".countByEmployeeId";
    String REP_COUNT_BY_EMP_ID_DEF = "select count(r) from Report as r where r.employeeId = :" + PARAM_EMP_ID;
    //授業のみ取得
    String REP_GET_GROUP = ENTITY_REP + ".getGroup";
    String REP_GET_GROUP_DEF = "select r from Report as r where r.lessonStyle = :" + TableConst.REP_GROUP;
    //個別のみ取得
    String REP_GET_PERSONAL = ENTITY_REP + "getPersonal";
    String REP_GET_PERSONAL_DEF = "select r from Report as r where r.lessonStyle = :" + TableConst.REP_PERSONAL;
    //employeeIdパラメータと一致する指導報告を取得
    String REP_GET_BY_EMP_ID = ENTITY_REP + ".getByEmployeeId";
    String REP_GET_BY_EMP_ID_DEF = "select r from Report as r where r.employeeId = :" + PARAM_EMP_ID;

    /*
     * commentsテーブル
     */
    //reportIdパラメータと一致するコメントを取得
    String COM_GET_BY_REP_ID = ENTITY_COM + ".getByReportId";
    String COM_GET_BY_REP_ID_DEF = "select c from Comment as c where c.reportId = :" + PARAM_REP_ID;
}
