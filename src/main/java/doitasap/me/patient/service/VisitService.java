package doitasap.me.patient.service;

import doitasap.me.patient.domain.Hospital;
import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.domain.Visit;
import doitasap.me.patient.dto.VisitDto;
import doitasap.me.patient.repository.VisitRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * 2022-06-26 오후 7:32
 * author DoitA$ap
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class VisitService {
    private final VisitRepository visitRepository;
    private final EntityManager em;

    @Transactional
    public void insert(VisitDto visitDto){
        Hospital hospital = em.find(Hospital.class, visitDto.getHospital());
        Patient patient = em.find(Patient.class, visitDto.getPatient());

        visitRepository.save(new Visit(visitDto.getVisitDate(), "방문중", patient, hospital));
    }
}
