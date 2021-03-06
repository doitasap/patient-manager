package doitasap.me.patient.web;

import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.HospitalDto;
import doitasap.me.patient.dto.PatientDto;
import doitasap.me.patient.service.CodeService;
import doitasap.me.patient.service.HospitalService;
import doitasap.me.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * 2022-06-25 오후 6:23
 * author DoitA$ap
 */
@Controller
@RequestMapping({"/", "/patient"})
@RequiredArgsConstructor
@Slf4j
public class PatientController {
    private final PatientService patientService;
    private final HospitalService hospitalService;
    private final CodeService codeService;

    @GetMapping({"/", "/searchAll"})
    public String searchAll(HttpSession session, @ModelAttribute PatientCriterion criterion, Model model) {
        log.info("환자 목록 조회..");
        log.debug("criterion : {} ", criterion);
        if (Objects.isNull(criterion.getSearchHospitalId())) {
            if (Objects.nonNull(session.getAttribute("hospitalId"))) {
                criterion.setSearchHospitalId(
                        Long.parseLong(String.valueOf(session.getAttribute("hospitalId")))
                );
            }
        }

        Page<PatientDto> list = patientService.searchAllReservation(criterion);
        log.debug("list : {} ", list);
        model.addAttribute("list", list);
        model.addAttribute("codes", codeService.searchAllCodes());

        List<HospitalDto> hospitals = hospitalService.searchAll();
        model.addAttribute("hospitals", hospitals);

        Long hospitalId = Objects.nonNull(criterion.getSearchHospitalId()) ?
                criterion.getSearchHospitalId() : Objects.nonNull(session.getAttribute("hospitalId")) ?
                (Long.parseLong(String.valueOf(session.getAttribute("hospitalId")))) : hospitals.get(0).getHospitalId();
        session.setAttribute("hospitalId", hospitalId);
        session.setAttribute("hospitalName", Objects.requireNonNull(hospitals.stream()
                .filter(h -> h.getHospitalId().longValue() == hospitalId)
                .findFirst().orElse(null)).getHospitalName());
        model.addAttribute("selectHospital", hospitalId);
        model.addAttribute("criterion", criterion);
        return "patient/list";
    }

    @GetMapping("/insertForm")
    public String insertForm(Model model) {
        model.addAttribute("codes", codeService.searchAllCodes());
        model.addAttribute("patient", new PatientDto());

        List<HospitalDto> hospitals = hospitalService.searchAll();
        model.addAttribute("hospitals", hospitals);
        return "patient/insertForm";
    }

    @GetMapping({"/{patientId}", "/{patientId}/{visitId}"})
    public String detail(@PathVariable Long patientId, @PathVariable(required = false) Long visitId, Model model) {
        log.info("환자 상세 조회..");
        model.addAttribute("patient", patientService.detail(patientId, visitId));
        model.addAttribute("codes", codeService.searchAllCodes());
        List<HospitalDto> hospitals = hospitalService.searchAll();
        model.addAttribute("hospitals", hospitals);
        return "patient/detail";
    }

    @PostMapping
    public RedirectView insert(@ModelAttribute @Validated PatientDto patientDto) {
        log.info("환자 등록..");
        log.debug("patientDto : {} ", patientDto);


        try {
            String enrollNum = UUID.randomUUID().toString().substring(0, 10);
            patientDto.setPatientEnrollNum(enrollNum);
            patientService.insert(patientDto);
        } catch (Exception e) {
            log.error("환자 등록 과정에서 오류 발생 : {} ", e.getMessage(), e);
        }
        return new RedirectView("patient/searchAll");
    }

    @PutMapping("/{patientId}")
    @ResponseBody
    public void update(@RequestBody @Validated PatientDto patientDto) {
        log.info("환자 수정..");
        log.debug("patientDto : {} ", patientDto);
        patientService.update(patientDto);
    }

    @DeleteMapping("/{patientId}")
    @ResponseBody
    public void delete(@PathVariable Long patientId) {
        log.info("환자 삭제..");
        patientService.delete(patientId);
    }
}
