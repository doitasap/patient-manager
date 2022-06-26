package doitasap.me.patient.web;

import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.HospitalDto;
import doitasap.me.patient.dto.PatientDto;
import doitasap.me.patient.dto.VisitDto;
import doitasap.me.patient.service.HospitalService;
import doitasap.me.patient.service.PatientService;
import doitasap.me.patient.service.VisitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Objects;

/**
 * 2022-06-25 오후 6:23
 * author DoitA$ap
 */
@RequestMapping("/visit")
@Controller
@RequiredArgsConstructor
@Slf4j
public class VisitController {
    private final PatientService patientService;
    private final HospitalService hospitalService;
    private final VisitService visitService;

    @GetMapping("/insertForm")
    public String insertForm(HttpSession session, Model model) {
        log.info("방문 등록 폼..");
        List<HospitalDto> hospitals = hospitalService.searchAll();
        model.addAttribute("hospitals", hospitals);

        if (Objects.isNull(session.getAttribute("hospitalId"))) {
            Long hospitalId = Objects.nonNull(session.getAttribute("hospitalId")) ?
                    (Long.parseLong(String.valueOf(session.getAttribute("hospitalId")))) : hospitals.get(0).getHospitalId();
            session.setAttribute("hospitalId", hospitalId);
            session.setAttribute("hospitalName", Objects.requireNonNull(hospitals.stream()
                    .filter(h -> h.getHospitalId().longValue() == hospitalId)
                    .findFirst().orElse(null)).getHospitalName());
            model.addAttribute("selectHospital", hospitalId);
        }
        Long hospitalId = Long.parseLong(String.valueOf(session.getAttribute("hospitalId")));
        PatientCriterion criterion = new PatientCriterion();
        criterion.setSearchHospitalId(hospitalId);
        List<PatientDto> patientList = patientService.searchAll(criterion);
        patientList.sort((p1, p2) -> p1.getPatientName().compareTo(p2.getPatientName()));
        model.addAttribute("patientList", patientList);
        model.addAttribute("visit", new VisitDto());

        return "visit/insertForm";
    }

    @PostMapping
    public RedirectView insert(@ModelAttribute @Validated VisitDto visitDto) {
        {
            log.info("방문 등록..");
            log.debug("visitDto : {} ", visitDto);

            try {
                visitService.insert(visitDto);
            } catch (Exception e) {
                log.error("방문 등록 과정에서 오류 발생 : {} ", e.getMessage(), e);
            }
            return new RedirectView("patient/searchAll");
        }
    }
}
