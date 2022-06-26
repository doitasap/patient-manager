package doitasap.me.patient.dto;

import doitasap.me.patient.domain.CodeGroup;
import lombok.Data;

/**
 * 2022-06-26 오전 12:40
 * author DoitA$ap
 */
@Data
public class CodeGroupDto {
    private String codeGroupId;
    private String codeGroupName;
    private String description;

    public CodeGroupDto(CodeGroup codeGroup) {
        this.codeGroupId = codeGroup.getCodeGroupId();
        this.codeGroupName = codeGroup.getCodeGroupName();
        this.description = codeGroup.getDescription();
    }
}
