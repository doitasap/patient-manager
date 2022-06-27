package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2022-06-27 오후 3:18
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class HospitalApiDto {
    private Long hospitalId;
    private String hospitalName;
    private String nursingInstitutionNum;
    private String ownerName;

    public HospitalApiDto(Hospital hospital) {
        this.hospitalId = hospital.getHospitalId();
        this.hospitalName = hospital.getHospitalName();
        this.nursingInstitutionNum = hospital.getNursingInstitutionNum();
        this.ownerName = hospital.getOwnerName();
    }
}
