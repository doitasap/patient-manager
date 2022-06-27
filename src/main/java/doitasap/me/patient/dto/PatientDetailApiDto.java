package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.domain.Visit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022-06-27 오후 3:09
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class PatientDetailApiDto {
    private Long patientId;
    private String patientName;
    private String patientEnrollNum;
    private String sexualCode;
    private String birth;
    private String phone;
    private HospitalApiDto hospital;
    private List<VisitApiDto> visitList;

    public PatientDetailApiDto(Patient patient) {
        this.patientId = patient.getPatientId();
        this.patientName = patient.getPatientName();
        this.patientEnrollNum = patient.getPatientEnrollNum();
        this.sexualCode = patient.getSexualCode();
        this.birth = patient.getBirth();
        this.phone = patient.getPhone();
        this.hospital = new HospitalApiDto(patient.getHospital());
    }

    public void setVisit(List<Visit> visitList){
        this.visitList = visitList.stream().map(visit -> new VisitApiDto(this.patientId, this.hospital.getHospitalId(), visit)).collect(Collectors.toList());
    }
}
