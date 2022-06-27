package doitasap.me.patient.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * 2022-06-25 오후 3:24
 * author DoitA$ap
 */
@Entity
@Table(name = "pm_visit", uniqueConstraints = {
        @UniqueConstraint(name="idAndPatientIdAndHospitalId", columnNames = {"visitId", "patient_id", "hospital_id"})
})
@NoArgsConstructor
@ToString
@Getter
public class Visit {
    @Id @GeneratedValue
    private Long visitId;
    private LocalDateTime visitDate;
    @Column(name = "visit_state", length = 10)
    private String visitState;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="patient_id")
    @ToString.Exclude
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="hospital_id")
    @ToString.Exclude
    private Hospital hospital;

    public Visit(LocalDateTime visitDate, String visitState) {
        this.visitDate = visitDate;
        this.visitState = visitState;
    }

    public Visit(LocalDateTime visitDate, String visitState, Patient patient, Hospital hospital) {
        this(visitDate, visitState);
        this.patient = patient;
        this.hospital = hospital;
    }
}
