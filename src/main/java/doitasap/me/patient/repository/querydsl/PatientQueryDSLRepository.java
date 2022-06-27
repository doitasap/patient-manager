package doitasap.me.patient.repository.querydsl;

import doitasap.me.patient.criterion.PatientApiCriterion;
import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.PatientApiDto;
import doitasap.me.patient.dto.PatientDto;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 2022-06-27 오전 9:08
 * author DoitA$ap
 */
public interface PatientQueryDSLRepository {
    /**
     * 동적 쿼리 처리를 위한 환자 검색
     * @return
     */
    List<PatientDto> searchAll(PatientCriterion criterion);

    /**
     * 환자 관리 페이징 검색
     * @return
     */
    Page<PatientDto> searchAllReservation(PatientCriterion criterion);


    /**
     * API 전용 전체 조회
     * @param criterion
     * @return
     */
    Page<PatientApiDto> searchAllForApi(PatientApiCriterion criterion);


}
