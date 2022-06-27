package doitasap.me.patient.criterion;

import lombok.Data;
import lombok.ToString;

/**
 * 2022-06-27 오후 2:17
 * author DoitA$ap
 */
@Data
@ToString(callSuper = true)
public class PatientApiCriterion {
    private String searchPatientName;
    private String searchEnrollNum;
    private String searchBirth;
    private int pageSize = 10;
    private int pageNo = 1;

    public int getOffset(){
        pageNo = Math.max(pageNo, 1);
        return (pageNo - 1) * pageSize;
    }
}
