package doitasap.me.patient.domain;

import doitasap.me.patient.dto.HospitalDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * 2022-06-25 오후 3:24
 * author DoitA$ap
 */
@Entity
@Table(name = "pm_hospital")
@NoArgsConstructor
@Getter
@ToString
public class Hospital {
    @Id @GeneratedValue
    private Long hospitalId;
    @Column(name = "hospital_name", length = 45)
    private String hospitalName;
    @Column(name = "nursing_institution_num", length = 20)
    private String nursingInstitutionNum;
    @Column(name = "owner_name", length = 10)
    private String ownerName;

    public Hospital(String hospitalName, String nursingInstitutionNum, String ownerName) {
        this.hospitalName = hospitalName;
        this.nursingInstitutionNum = nursingInstitutionNum;
        this.ownerName = ownerName;
    }

    public Hospital(HospitalDto dto) {
        this.hospitalName = dto.getHospitalName();
        this.nursingInstitutionNum = dto.getNursingInstitutionNum();
        this.ownerName = dto.getOwnerName();

    }
}
