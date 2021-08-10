package constants;

public enum AttributeConst {
    SUB_HUM(0),
    SUB_SCI(1),
    FULL_TIME(0),
    PART_TIME(1),
    DEL_TRUE(0),
    DEL_FALSE(1),

    PERSONAL(0),
    GROUP(1),

    READ(1),
    UNREAD(0),

    FLUSH("flush"),
    TOKEN("_token"),
    PAGE("page"),
    MAX_ROW("maxRow"),

    EMPLOYEE("employee"),
    EMPLOYEES("employees"),
    EMP_COUNT("employees_count"),
    EMP_WORK_STYLE("work_style"),
    EMP_NAME("name"),
    EMP_SUBJECT("subject"),
    EMP_MAIL("mail_address"),
    EMP_ID("id"),
    EMP_PASS("password")

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
