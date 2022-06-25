package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Hospital;
import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.domain.Visit;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;


@SpringBootTest
@Transactional
class VisitRepositoryTest {
    @Autowired
    private EntityManager em;

    @Autowired
    private VisitRepository visitRepository;

    @BeforeEach
    void insertData(){
        Hospital hospital = new Hospital("테스트 병원", "1234", "박회장");
        em.persist(hospital);

        Patient patient1 = new Patient("환자1", "1번환자", "M", "920502", "010-1234-1234", hospital);
        Patient patient2 = new Patient("환자2", "2번환자", "F", "950702", "010-3232-1111", hospital);
        Patient patient3 = new Patient("환자3", "3번환자", "M", "880102", "010-2356-6666", hospital);

        em.persist(patient1);
        em.persist(patient2);
        em.persist(patient3);

        Visit visit = new Visit(LocalDateTime.of(2022, 6, 22, 11, 30), "2", patient1, hospital);
        Visit visit2 = new Visit(LocalDateTime.of(2022, 6, 24, 18, 0), "3", patient2, hospital);
        Visit visit3 = new Visit(LocalDateTime.of(2022, 6, 25, 18, 0), "1", patient3, hospital);

        em.persist(visit);
        em.persist(visit2);
        em.persist(visit3);

        em.flush();
        em.clear();
    }

    @Test
    @DisplayName("방문 조회")
    void select(){
        List<Visit> all = visitRepository.findAllByHospitalName("테스트 병원");
        System.out.println("all = " + all);
    }

}