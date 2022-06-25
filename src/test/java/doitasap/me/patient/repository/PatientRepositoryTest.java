package doitasap.me.patient.repository;

import doitasap.me.patient.domain.CodeGroup;
import doitasap.me.patient.domain.Hospital;
import doitasap.me.patient.domain.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class PatientRepositoryTest {
    @Autowired
    private EntityManager em;
    @Autowired
    private PatientRepository patientRepository;

    @BeforeEach
    void data_insert() {
        Hospital hospital = new Hospital("테스트 병원", "1234", "박회장");
        em.persist(hospital);

        Patient patient1 = new Patient("환자1", "1번환자", "M", "920502", "010-1234-1234", hospital);
        Patient patient2 = new Patient("환자2", "2번환자", "F", "950702", "010-3232-1111", hospital);
        Patient patient3 = new Patient("환자3", "3번환자", "M", "880102", "010-2356-6666", hospital);

        em.persist(patient1);
        em.persist(patient2);
        em.persist(patient3);

        em.flush();
        em.clear();
    }


    @Test
    @DisplayName("환자 전체 조회")
    void selectAll() {
        List<Patient> all = patientRepository.findAll();

        assertThat(all.size()).isEqualTo(3);
        assertThat(all.stream().map(Patient::getPatientName).collect(Collectors.toList()))
                .containsExactly("환자1", "환자2", "환자3");
    }

    @Test
    @DisplayName("환자 조회")
    void selectOne() {
        Optional<Patient> patient2Optional = patientRepository.findPatientByPatientName("환자2");
        assertThat(patient2Optional.isPresent()).isTrue();
        Patient patient = patient2Optional.get();
        assertThat(patient.getPhone()).isEqualTo("010-3232-1111");

        assertThat(patientRepository.findPatientByPatientName("환자6").isPresent()).isFalse();
    }

    @Test
    @DisplayName("환자 등록")
    public void insert() {
        //given
        Hospital hospital = em.find(Hospital.class, 1L);
        Patient newPatient = new Patient(
                "뉴페이스", "998877",
                "F", "2011-03-03", "010-0002-0101", hospital
        );


        //when
        patientRepository.save(newPatient);

        //then
        List<Patient> all = patientRepository.findAll();
        assertThat(all.size()).isEqualTo(4);
        assertThat(all.stream().map(Patient::getPatientName).collect(Collectors.toList()))
                .containsExactly("환자1", "환자2", "환자3", "뉴페이스");
    }

    @Test
    @DisplayName("환자 수정")
    public void update() {
        Patient patient2 = Objects.requireNonNull(patientRepository.findPatientByPatientName("환자2").orElse(null));
        patient2.setPatientName("수정");
        if(em.contains(patient2)){
            em.merge(patient2);
        }
        em.flush();
        em.clear();

        Patient find = Objects.requireNonNull(patientRepository.findPatientByPatientName("수정").orElse(null));
        assertThat(find).isNotNull();
        assertThat(find.getBirth()).isEqualTo("950702");
    }

    @Test
    @DisplayName("환자 삭제")
    public void delete() {
        Patient patient2 = Objects.requireNonNull(patientRepository.findPatientByPatientName("환자2").orElse(null));
        patientRepository.delete(patient2);
        assertThat(patientRepository.findAll().size()).isEqualTo(2);
    }

    @Test
    @Sql(value = "/insert.sql")
    @DisplayName("초기 설정 파일 테스트")
    void setting_test() {
        CodeGroup sexualCodeGroup = em.find(CodeGroup.class, "성별코드");
        String description = sexualCodeGroup.getDescription();
        System.out.println("description = " + description);
        assertThat(sexualCodeGroup).isNotNull();

    }

}