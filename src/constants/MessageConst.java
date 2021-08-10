package constants;

public enum MessageConst {
    //バリデーション
    E_NONAME("氏名が未入力です"),
    E_NOPASSWORD("パスワードが未入力です"),
    E_ISNT_OVER("パスワードが短すぎます(8文字以上)"),
    E_NOMAILADDRESS("メールアドレスが未入力です"),
    E_ISNT_MAILADDRES("メールアドレスを入力してください"),
    E_MAILADDRESS_EXIST("入力されたメールアドレスは既に登録されています"),
    E_NOCONTENT("指導報告を入力してください")
    ;

    private final String text;


    private MessageConst(final String text) {
        this.text = text;
    }


    public String getMessage() {
        return this.text;
    }
}
