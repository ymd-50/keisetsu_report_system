package models;

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
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import constants.QueryConst;
import constants.TableConst;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = TableConst.TABLE_COM)
@NamedQueries({
    @NamedQuery(
            name = QueryConst.COM_GET_BY_REP_ID,
            query = QueryConst.COM_GET_BY_REP_ID_DEF
            )
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment {
    //主キー
    @Id
    @Column(name = TableConst.COM_ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //作成者
    @ManyToOne
    @JoinColumn(name = TableConst.COM_EMP_ID, nullable = false)
    private Employee employee;

    //コメント元の指導報告
    @ManyToOne
    @JoinColumn(name = TableConst.COM_REP_ID, nullable = false)
    private Report report;

    //コメント
    @Lob
    @Column(name = TableConst.COM_CONTENT, nullable = false)
    private String content;

    //作成日時
    @Column(name = TableConst.COM_CREATED_AT, nullable = false)
    private LocalDateTime createdAt;

    //更新日時
    @Column(name = TableConst.COM_UPDATED_AT, nullable = false)
    private LocalDateTime updatedAt;

    //既読フラッグ
    @Column(name = TableConst.COM_READ_FLAG, nullable = false)
    private Integer readFlag;
}
