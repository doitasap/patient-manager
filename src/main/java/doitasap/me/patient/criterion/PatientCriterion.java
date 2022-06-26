package doitasap.me.patient.criterion;

/**
 * 2022-06-25 오후 7:55
 * author DoitA$ap
 */

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
public class PatientCriterion extends Pagination{
    private Long searchHospitalId;
    private String searchPatientName;
    private String searchEnrollNum;
    private String searchBirth;
}
