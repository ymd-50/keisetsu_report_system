package constants;

public enum ForwardConst {
    ACT("action"),
    ACT_TOP("Top"),
    ACT_EMP("Employee"),
    ACT_REP("Report"),
    ACT_AUTH("Auth"),

    CMD("command"),
    CMD_INDEX("index"),
    CMD_SHOW("show"),
    CMD_SHOW_LOGIN("showLogin"),
    CMD_LOGIN("login"),
    CMD_LOGOUT("logout"),
    CMD_NEW("entryNew"),
    CMD_CREATE("create"),
    CMD_EDIT("edit"),
    CMD_UPDATE("update"),
    CMD_DESTROY("destroy"),


    FW_ERR_UNK("errors/unknown"),

    FW_EMP_INDEX("employees/index"),
    FW_EMP_NEW("employees/new"),
    FW_EMP_SHOW("employees/show"),
    FW_EMP_EDIT("employees/edit")


    ;


    private final String text;

    private ForwardConst(final String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }


}
