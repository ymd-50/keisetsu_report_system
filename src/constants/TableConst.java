package constants;

public interface TableConst {
    //persistence-unit名
    String PERSISTENCE_UNIT_NAME = "keisetsu_report_system";

    //employeesテーブル
    String TABLE_EMP = "employees";
    //カラム
    String EMP_ID = "id";
    String EMP_NAME = "name";
    String EMP_SUB = "subject";
    String EMP_WORK_STYLE = "work_style";
    String EMP_MAIL = "mail_address";
    String EMP_PASS = "password";
    String EMP_DEL_FLAG = "delete_flag";
    String EMP_CREATED_AT = "created_at";
    String EMP_UPDATED_AT = "updated_at";

    int EMP_SUB_HUM = 0;
    int EMP_SUB_SCI = 1;
    int EMP_FULL_TIME = 0;
    int EMP_PART_TIME = 1;
    int EMP_DEL_TRUE = 1;
    int EMP_DEL_FALSE = 0;

    //reportsテーブル
    String TABLE_REP = "reports";
    //カラム
    String REP_ID = "id";
    String REP_EMP_ID = "employee_id";
    String REP_LESSON_STYLE ="lesson_style";
    String REP_DATE = "report_date";
    String REP_CLASS_NUM = "class_number";
    String REP_CLASS_NAME = "class_name";
    String REP_GRADE = "grade";
    String REP_SUB = "subject";
    String REP_TITLE = "title";
    String REP_CONTENT = "content";
    String REP_ABSENTEE = "absentee";
    String REP_CREATED_AT = "created_at";
    String REP_UPDATED_AT = "updated_at";
    String REP_READ_FLAG= "read_flag";

    int REP_PERSONAL = 0;
    int REP_GROUP = 1;
    int REP_READE = 1;
    int REP_UNREAD = 0;


    //commentsテーブル
    String TABLE_COM = "comments";
    //カラム
    String COM_ID = "id";
    String COM_EMP_ID = "employee_id";
    String COM_REP_ID = "report_id";
    String COM_CONTENT = "content";
    String COM_CREATED_AT= "created_at";
    String COM_UPDATED_AT = "updated_at";
    String COM_READ_FLAG = "read_flag";

    int COM_READE = 1;
    int COM_UNREAD = 0;

}
