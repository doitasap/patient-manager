package doitasap.me.patient.service;

import doitasap.me.patient.criterion.PatientApiCriterion;
import doitasap.me.patient.domain.Hospital;
import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.dto.PatientApiDto;
import doitasap.me.patient.dto.PatientDetailApiDto;
import doitasap.me.patient.dto.VisitApiDto;
import doitasap.me.patient.repository.PatientRepository;
import doitasap.me.patient.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 2022-06-27 오후 1:52
 * author DoitA$ap
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class PatientApiService {

    private final EntityManager em;
    private final PatientRepository patientRepository;
    private final VisitRepository visitRepository;

    public Page<PatientApiDto> searchAll(PatientApiCriterion criterion) {
        return patientRepository.searchAllForApi(criterion);
    }

    public PatientDetailApiDto detail(Long patientId) {
        PatientDetailApiDto detail = new PatientDetailApiDto(patientRepository.detail(patientId));
        detail.setVisitList(
                visitRepository.findAllByPatient(patientId).stream().map(
                        visit -> new VisitApiDto(patientId, detail.getHospital().getHospitalId(), visit)
                ).collect(Collectors.toList()));

        return detail;
    }

    @Transactional
    public PatientApiDto insert(PatientApiDto patientApiDto) {
        Hospital hospital = em.find(Hospital.class, patientApiDto.getHospitalId());
        Patient insert = new Patient(patientApiDto, hospital);
        insert = patientRepository.save(insert);
        return new PatientApiDto(insert);
    }

    @Transactional
    public PatientApiDto update(PatientApiDto patientApiDto) {
        Patient patient = Objects.requireNonNull(patientRepository.findById(patientApiDto.getPatientId()).orElse(null));
        patient.changeInfo(patientApiDto);
        if (em.contains(patient)) {
            em.merge(patient);
        }
        return new PatientApiDto(patient);
    }

    @Transactional
    public void delete(Long patientId) {
        patientRepository.delete(em.find(Patient.class, patientId));
    }
}
