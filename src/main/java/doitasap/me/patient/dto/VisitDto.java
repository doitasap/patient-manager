package doitasap.me.patient.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
    private LocalDateTime visitDate;
    private String visitState;
}
