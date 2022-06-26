package doitasap.me.patient.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 2022-06-25 오후 3:25
 * author DoitA$ap
 */
@Entity
@Table(name = "pm_code")
@Getter
@ToString
public class Code {
    @Id
    @Column(name = "code_id", length = 10)
    private String codeId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "code_group_id")
    @ToString.Exclude
    private CodeGroup codeGroup;

    @Column(name = "code_name", length = 10)
    private String codeName;
}
