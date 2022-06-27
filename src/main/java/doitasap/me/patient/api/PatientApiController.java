package doitasap.me.patient.api;

import doitasap.me.patient.criterion.PatientApiCriterion;
import doitasap.me.patient.dto.PatientApiDto;
import doitasap.me.patient.service.PatientApiService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 2022-06-27 오후 1:30
 * author DoitA$ap
 */
@RestController
@RequestMapping("/v1/patient")
@RequiredArgsConstructor
@Slf4j
public class PatientApiController {
    private final PatientApiService patientService;

    @GetMapping("/searchAll")
    public Object searchAll(PatientApiCriterion criterion) {
        log.info("환자 전체 검색");
        log.debug("criterion : {} ", criterion);
        return patientService.searchAll(criterion);
    }

    @GetMapping("/{patientId}")
    public Object detail(@PathVariable Long patientId) {
        log.info("환자 상세 조회 : {}", patientId);
        return patientService.detail(patientId);
    }

    @PostMapping
    public Map<String, Object> insert(@Valid @RequestBody PatientApiDto patientDto) {
        log.info("환자 등록..");
        log.debug("patientDto : {} ", patientDto);

        boolean result = false;

        try {
            String enrollNum = UUID.randomUUID().toString().substring(0, 10);
            patientDto.setPatientEnrollNum(enrollNum);
            patientDto = patientService.insert(patientDto);
            result = true;
        } catch (ConstraintViolationException e) {
            log.error("동일한 병원ID 및  환자등록번호 발생 : {} ", e.getMessage(), e);
            //TODO: 예외처리
        } catch (Exception e) {
            log.error("환자 등록 과정에서 오류 발생 : {} ", e.getMessage(), e);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", result);
        resultMap.put("patient", patientDto);

        return resultMap;
    }

    @PutMapping("/{patientId}")
    public Map<String, Object> update(@RequestBody PatientApiDto patientDto, @PathVariable String patientId) {
        log.info("환자 수정.. id : {}", patientId);
        log.debug("patientDto : {} ", patientDto);
        boolean result = false;
        try {
            patientDto = patientService.update(patientDto);
            result = true;
        } catch (Exception e) {
            log.error("환자 수정 과정에서 오류 발생 : {} ", e.getMessage(), e);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", result);
        resultMap.put("patient", patientDto);

        return resultMap;
    }

    @DeleteMapping("/{patientId}")
    public Map<String, Object> delete(@PathVariable Long patientId) {
        log.info("환자 삭제.. id : {}", patientId);
        boolean result = false;
        try {
            patientService.delete(patientId);
            result = true;
        } catch (Exception e) {
            log.error("환자 삭제 과정에서 오류 발생 : {} ", e.getMessage(), e);
        }

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("success", result);

        return resultMap;
    }

}
