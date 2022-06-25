package doitasap.me.patient.domain;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 2022-06-25 오후 3:25
 * author DoitA$ap
 */
@Entity
@Table(name = "pm_code_group")
@Getter
@ToString
public class CodeGroup {
    @Id
    @Column(name = "code_group_id", length = 10)
    private String codeGroupId;
    @Column(name = "code_group_name", length = 10)
    private String codeGroupName;
    @Column(name = "description", length = 10)
    private String description;
}
