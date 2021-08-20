package constants;

public enum AttributeConst {
    SUB_HUM(0),
    SUB_SCI(1),
    FULL_TIME(0),
    PART_TIME(1),
    DEL_TRUE(1),
    DEL_FALSE(0),

    PERSONAL(0),
    GROUP(1),
    ORDER_BY_REP_DATE(0),
    ORDER_BY_UPDATE(1),
    GRADE1(1),
    GRADE2(2),
    GRADE3(3),

    READ(1),
    UNREAD(0),

    FLUSH("flush"),
    TOKEN("_token"),
    PAGE("page"),
    MAX_ROW("maxRow"),
    ERR("errors"),

    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_WORK_STYLE("work_style"),
    EMP_NAME("name"),
    EMP_SUBJECT("subject"),
    EMP_MAIL("mail_address"),
    EMP_ID("id"),
    EMP_PASS("password"),

    LOGIN_EMP("login_employee"),

    REPORT("report"),
    REPORTS("reports"),
    REP_COUNT("report_count"),
    REP_LESSON_STYLE("lesson_style"),
    REP_DATE("report_date"),
    REP_GRADE("grade"),
    REP_CLASS_NAME("class_name"),
    REP_SUBJECT("subject"),
    REP_TITLE("title"),
    REP_CONTENT("content"),
    REP_ABSENTEE("absentee"),
    REP_ID("report_id"),


    REP_ORDER_BY("order_by"),
    REP_PARAM_LESSON_STYLE("lessonStyle")


    ;

    private final String text;
    private final Integer i;

    private AttributeConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private AttributeConst(final Integer i) {
        this.text = null;
        this.i = i;
    }

    public String getValue() {
        return this.text;
    }

    public Integer getIntegerValue() {
        return this.i;
    }
}
