package doitasap.me.patient.web;

import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.PatientDto;
import doitasap.me.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 2022-06-25 오후 6:23
 * author DoitA$ap
 */
@Controller
@RequestMapping("/patient")
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    public final PatientService service;

    @GetMapping("/searchAll")
    @ResponseBody
    public List<PatientDto> searchAll(@ModelAttribute PatientCriterion criterion){
        log.info("환자 목록 조회..");
        List<PatientDto> list = service.searchAll(criterion);
        log.debug("list : {} ", list);
        return list;
    }

    @GetMapping("/{patientId}")
    @ResponseBody
    public PatientDto detail(@PathVariable Long patientId){
        log.info("환자 조회..");
        return service.detail(patientId);
    }

    @PostMapping
    @ResponseBody
    public void insert(@RequestBody @Validated PatientDto patientDto){
        log.info("환자 등록..");
        log.debug("patientDto : {} ", patientDto);

        try {
            service.insert(patientDto);
        } catch (Exception e) {
            log.error("환자 등록 과정에서 오류 발생 : {} ", e.getMessage(), e);
        }
    }

    @PutMapping("/{patientId}")
    @ResponseBody
    public void update(@RequestBody @Validated PatientDto patientDto){
        log.info("환자 수정..");
        log.debug("patientDto : {} ", patientDto);
        service.update(patientDto);
    }

    @DeleteMapping("/{patientId}")
    @ResponseBody
    public void delete(@PathVariable Long patientId){
        log.info("환자 삭제..");
        service.delete(patientId);
    }

}
