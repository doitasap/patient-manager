package doitasap.me.patient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 2022-06-26 오후 4:20
 * author DoitA$ap
 */
@Data
@NoArgsConstructor
public class VisitDto {
    private Long patient;
    private Long hospital;
    private LocalDateTime visitDate;
    private String visitState;
}
