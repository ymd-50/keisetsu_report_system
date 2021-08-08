package constants;

public enum ForwardConst {
    ACT("action"),
    ACT_AUTH("Auth"),

    CMD("command"),


    FW_ERR_UNK("errors/unknown")
    ;


    private final String text;

    private ForwardConst(final String text) {
        this.text = text;
    }

    public String getValue() {
        return this.text;
    }


}
