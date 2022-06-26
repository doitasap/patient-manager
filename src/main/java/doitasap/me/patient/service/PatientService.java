package doitasap.me.patient.service;

import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.domain.Hospital;
import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.dto.PatientDto;
import doitasap.me.patient.repository.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PatientService {
    private final PatientRepository patientRepository;
    private final EntityManager em;

    public List<PatientDto> searchAll(PatientCriterion criterion){
        List<PatientDto> list = patientRepository.searchAll().stream().map(PatientDto::new).collect(Collectors.toList());
        list = setCriterion(criterion, list);
        return list;
    }
    public List<PatientDto> searchAllReservation(PatientCriterion criterion){
        List<PatientDto> list = patientRepository.searchAllReservation().stream().map(PatientDto::new).collect(Collectors.toList());
        list = setCriterion(criterion, list);
        return list;
    }

    private List<PatientDto> setCriterion(PatientCriterion criterion, List<PatientDto> list) {
        if (Objects.nonNull(criterion.getSearchHospitalId())) {
            list = list.stream()
                    .filter(p -> p.getHospital().longValue() == criterion.getSearchHospitalId().longValue())
                    .collect(Collectors.toList());
        }
        if (Objects.nonNull(criterion.getSearchPatientName())) {
            list = list.stream()
                    .filter(p -> p.getPatientName().contains(criterion.getSearchPatientName()))
                    .collect(Collectors.toList());
        }
        return list;
    }

    public PatientDto detail(Long id, Long visitId){
        if(Objects.nonNull(visitId)){
            return new PatientDto(Objects.requireNonNull(patientRepository.detail(id, visitId)));
        }else{
            return new PatientDto(Objects.requireNonNull(patientRepository.detail(id)));
        }
    }

    @Transactional
    public void insert(PatientDto patientDto){
        Hospital hospital = em.find(Hospital.class, patientDto.getHospital());
        Patient entity = new Patient(patientDto);
        entity.setHospital(hospital);
        patientRepository.save(entity);
    }

    @Transactional
    public void update(PatientDto patientDto){
        Patient patient = Objects.requireNonNull(patientRepository.findById(patientDto.getId()).orElse(null));
        patient.changeInfo(patientDto);
        if(em.contains(patient)){
            em.merge(patient);
        }
    }

    @Transactional
    public void delete(Long patientId){
        patientRepository.delete(em.find(Patient.class, patientId));
    }
}
