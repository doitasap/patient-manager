package doitasap.me.patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import doitasap.me.patient.domain.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * 2022-06-27 오후 1:32
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class PatientApiDto {
    private Long patientId;
    @NotNull
    private Long hospitalId;
    @NotNull
    private String patientName;
    private String patientEnrollNum;
    private String sexualCode;
    private String birth;
    private String phone;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime recentDate;

    public PatientApiDto(Patient patient) {
        this.patientId = patient.getPatientId();
        this.patientName = patient.getPatientName();
        this.patientEnrollNum = patient.getPatientEnrollNum();
        this.sexualCode = patient.getSexualCode();
        this.birth = patient.getBirth();
        this.phone = patient.getPhone();
        this.hospitalId = patient.getHospital().getHospitalId();
//        this.recentDate = patient.get
    }

    @QueryProjection
    public PatientApiDto(Long patientId, String patientName, String patientEnrollNum, String sexualCode, String birth, String phone, LocalDateTime recentDate) {
        this.patientId = patientId;
        this.patientName = patientName;
        this.patientEnrollNum = patientEnrollNum;
        this.sexualCode = sexualCode;
        this.birth = birth;
        this.phone = phone;
        this.recentDate = recentDate;
    }
}
