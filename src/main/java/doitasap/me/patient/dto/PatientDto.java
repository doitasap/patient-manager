package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 2022-06-25 오후 7:48
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class PatientDto {
    private Long id;
//    @NotNull
    private String patientName;
    private String patientEnrollNum;
    private String sexualCode;
    private String birth;
    private String phone;
    private HospitalDto hospital;

    public PatientDto(Patient patient) {
        this.id = patient.getPatientId();
        this.patientName = patient.getPatientName();
        this.patientEnrollNum = patient.getPatientEnrollNum();
        this.sexualCode = patient.getSexualCode();
        this.birth = patient.getBirth();
        this.phone = patient.getPhone();
        this.hospital = new HospitalDto(patient.getHospital());
    }
}

