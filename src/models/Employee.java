package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.QueryConst;
import constants.TableConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = TableConst.TABLE_EMP)
@NamedQueries({
    @NamedQuery(
            name = QueryConst.EMP_COUNT,
            query = QueryConst.EMP_COUNT_DEF
            ),
    @NamedQuery(
            name = QueryConst.EMP_GET_ALL,
            query = QueryConst.EMP_GET_ALL_DEF
            ),
    @NamedQuery(
            name = QueryConst.EMP_GET_BY_MAIL_AND_PASS,
            query = QueryConst.EMP_GET_BY_MAIL_AND_PASS_DEF

            ),
    @NamedQuery(
            name = QueryConst.EMP_GET_FULL_TIME,
            query = QueryConst.EMP_GET_FULL_TIME_DEF
            ),
    @NamedQuery(
            name = QueryConst.EMP_GET_PART_TIME,
            query = QueryConst.EMP_GET_PART_TIME_DEF
            ),
    @NamedQuery(
            name = QueryConst.EMP_COUNT_BY_MAIL,
            query = QueryConst.EMP_COUNT_BY_MAIL_DEF
            )

})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee {

    //id
    @Id
    @Column(name = TableConst.EMP_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //氏名
    @Column(name = TableConst.EMP_NAME, nullable = false)
    private String name;

    //担当科目
    @Column(name = TableConst.EMP_SUB, nullable = false)
    private Integer subject;

    //勤務形態
    @Column(name = TableConst.EMP_WORK_STYLE, nullable = false)
    private Integer workStyle;

    //メールアドレス
    @Column(name =TableConst.EMP_MAIL, nullable = false, unique = true)
    private String mailAddress;

    //パスワード
    @Column(name =TableConst.EMP_PASS, length = 64, nullable = false)
    private String password;

    //論理削除フラッグ
    @Column(name =TableConst.EMP_DEL_FLAG, nullable = false)
    private Integer deleteFlag;

    //作成日時
    @Column(name =TableConst.EMP_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column(name =TableConst.EMP_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;


}
