package doitasap.me.patient.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

/**
 * 2022-06-25 오후 3:24
 * author DoitA$ap
 */
@Entity
@Table(name = "pm_patient", uniqueConstraints = {
        @UniqueConstraint(name = "idAndHospitalId", columnNames = {"patientId", "hospital_id"})
})
@Getter @Setter
@NoArgsConstructor
@ToString
public class Patient {
    @Id @GeneratedValue
    private Long patientId;
    @Column(name = "patient_name", length = 45)
    private String patientName;
    @Column(name="patient_enroll_num", length = 13)
    private String patientEnrollNum;
    @Column(name="sexual_code", length = 10)
    private String sexualCode;
    @Column(name="birth", length = 10)
    private String birth;
    @Column(name="phone", length = 20)
    private String phone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_id")
    @ToString.Exclude
    private Hospital hospital;

    public Patient(String patientName, String patientEnrollNum, String sexualCode, String birth, String phone, Hospital hospital) {
        this(patientName, patientEnrollNum, sexualCode, birth, phone);
        this.hospital = hospital;
    }
    public Patient(String patientName, String patientEnrollNum, String sexualCode, String birth, String phone) {
        this.patientName = patientName;
        this.patientEnrollNum = patientEnrollNum;
        this.sexualCode = sexualCode;
        this.birth = birth;
        this.phone = phone;
    }


}
