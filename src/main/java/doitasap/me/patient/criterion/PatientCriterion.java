package doitasap.me.patient.criterion;

/**
 * 2022-06-25 오후 7:55
 * author DoitA$ap
 */

import lombok.Data;

@Data
public class PatientCriterion {
    private Long searchHospitalId;
    private String searchPatientName;
}
