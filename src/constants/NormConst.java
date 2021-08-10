package constants;

public enum NormConst {
    PASS_MUST_OVER(8),
    MAIL_PATTERN("^([a-zA-Z0-9])+([a-zA-Z0-9\\._-])*@([a-zA-Z0-9_-])+([a-zA-Z0-9\\._-]+)+$")
    ;

    private final String text;
    private final Integer i;

    private NormConst(final String text) {
        this.text = text;
        this.i = null;
    }

    private NormConst(final Integer i) {
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
