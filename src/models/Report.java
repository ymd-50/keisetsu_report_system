package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import constants.TableConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = TableConst.TABLE_REP)
@NamedQueries({
    /*@NamedQuery(
            name = QueryConst.REP_COUNT_BY_EMP_ID,
            query = QueryConst.REP_COUNT_BY_EMP_ID_DEF
            )*/
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Report {
    //主キー
    @Id
    @Column(name = TableConst.REP_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //作成者
    @ManyToOne
    @JoinColumn(name = TableConst.REP_EMP_ID, nullable = false)
    private Employee employee;

    //指導形態
    @Column(name = TableConst.REP_LESSON_STYLE, nullable = false)
    private Integer lessonStyle;

    //指導日時
    @Column(name = TableConst.REP_DATE, nullable = false)
    private LocalDate reportDate;

    //コマ数
    @Column(name = TableConst.REP_CLASS_NUM, nullable = false)
    private Integer classNumber;

    //授業名
    @Column(name = TableConst.REP_CLASS_NAME, nullable = false)
    private String className;

    //学年
    @Column(name = TableConst.REP_GRADE, nullable = false)
    private Integer grade;

    //教科
    @Column(name = TableConst.REP_SUB, nullable = false)
    private String subject;

    //単元名など
    @Column(name = TableConst.REP_TITLE,length = 255, nullable = false)
    private String title;

    //指導内容
    @Lob
    @Column(name = TableConst.REP_CONTENT, nullable = false)
    private String content;

    //欠席者
    @Lob
    @Column(name = TableConst.REP_ABSENTEE, nullable = false)
    private String absentee;

    //作成日時
    @Column(name = TableConst.REP_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column(name = TableConst.REP_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    //既読フラッグ
    @Column(name = TableConst.REP_READ_FLAG, nullable = false)
    private Integer readFlag;
}
