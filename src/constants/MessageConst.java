package constants;

public enum MessageConst {
    I_REGISTERED("登録完了しました"),
    I_UPDATED("更新完了しました"),
    I_DESTROID("削除完了しました"),
    I_LOGINED("ログイン完了しました"),

    //バリデーション
    E_NONAME("氏名が未入力です"),
    E_NOPASSWORD("パスワードが未入力です"),
    E_ISNT_OVER("パスワードが短すぎます(8文字以上)"),
    E_NOMAILADDRESS("メールアドレスが未入力です"),
    E_ISNT_MAILADDRES("メールアドレスを入力してください"),
    E_MAILADDRESS_EXIST("入力されたメールアドレスは既に登録されています"),
    E_NOCONTENT("指導報告が未入力です"),
    E_NOACCOUNT("メールアドレスまたはパスワードが間違っています"),
    E_NOMAIL_OR_NOPASS("未入力の項目があります"),
    E_REPORT_DATE("指導日時が現在より先になっています"),
    E_NOCLASSNAME("クラス名または生徒名が未入力です"),
    E_NOSUBJECT("教科が未入力です"),
    E_NOTITLE("単元名が未入力です"),
    ;

    private final String text;


    private MessageConst(final String text) {
        this.text = text;
    }


    public String getMessage() {
        return this.text;
    }
}
