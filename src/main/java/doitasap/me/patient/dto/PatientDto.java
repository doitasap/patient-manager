package doitasap.me.patient.dto;

import doitasap.me.patient.domain.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;

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
    private Long hospital;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime visitDate;
    private String visitState;
    private Long visitId;

    public PatientDto(Patient patient) {
        this.id = patient.getPatientId();
        this.patientName = patient.getPatientName();
        this.patientEnrollNum = patient.getPatientEnrollNum();
        this.sexualCode = patient.getSexualCode();
        this.birth = patient.getBirth();
        this.phone = patient.getPhone();
        this.hospital = patient.getHospital().getHospitalId();
    }

    public PatientDto(Map<String, Object> map){
        this.id = Long.parseLong(String.valueOf(map.get("id")));
        this.patientName = String.valueOf(map.get("patientName"));
        this.patientEnrollNum = String.valueOf(map.get("patientEnrollNum"));
        this.sexualCode = String.valueOf(map.get("sexualCode"));
        this.birth = String.valueOf(map.get("birth"));
        this.phone = String.valueOf(map.get("phone"));
        this.hospital = Long.parseLong(String.valueOf(map.get("hospital")));
        if (Objects.nonNull(map.get("visitId"))) {
            this.visitId = Long.parseLong(String.valueOf(map.get("visitId")));
            this.visitDate = LocalDateTime.parse(String.valueOf(map.get("visitDate")));
            this.visitState = String.valueOf(map.get("visitState"));
        }
    }


}

