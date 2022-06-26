package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Code;
import lombok.Data;

/**
 * 2022-06-26 오전 12:39
 * author DoitA$ap
 */
@Data
public class CodeDto {
    private String codeId;
    private String codeName;
    private CodeGroupDto codeGroup;

    public CodeDto(Code code) {
        this.codeId = code.getCodeId();
        this.codeName = code.getCodeName();
        this.codeGroup = new CodeGroupDto(code.getCodeGroup());
    }
}
