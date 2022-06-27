package doitasap.me.patient.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import doitasap.me.patient.domain.Visit;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 2022-06-27 오후 3:19
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class VisitApiDto {
    private Long patient;
    private Long hospital;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime visitDate;
    private String visitState;

    public VisitApiDto(Long patientId, Long hospitalId, Visit visit) {
        this.patient = patientId;
        this.hospital = hospitalId;
        this.visitDate = visit.getVisitDate();
        this.visitState = visit.getVisitState();
    }
}
