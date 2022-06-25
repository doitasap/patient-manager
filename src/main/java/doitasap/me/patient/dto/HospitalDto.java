package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Hospital;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2022-06-25 오후 7:50
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class HospitalDto {
    private Long hospitalId;
    private String hospitalName;
    private String nursingInstitutionNum;
    private String ownerName;

    public HospitalDto(Hospital hospital) {
        this.hospitalId = hospital.getHospitalId();
        this.hospitalName = hospital.getHospitalName();
        this.nursingInstitutionNum = hospital.getNursingInstitutionNum();
        this.ownerName = hospital.getOwnerName();
    }
}
