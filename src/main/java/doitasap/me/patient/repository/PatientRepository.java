package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Patient;
import doitasap.me.patient.repository.querydsl.PatientQueryDSLRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long>, PatientQueryDSLRepository {
//public interface PatientRepository extends JpaRepository<Patient, Long>{
    Optional<Patient> findPatientByPatientName(String patientName);

    @Query("select p from Patient p join fetch Hospital h on p.hospital = h")
    List<Patient> searchAll();
    @Query("select " +
            "p.patientId as id, p.patientName as patientName, p.hospital.hospitalId as hospital, " +
            "p.sexualCode as sexualCode, p.patientEnrollNum as patientEnrollNum, " +
            "p.birth as birth, p.phone as phone, v.visitDate as visitDate, " +
            "v.visitState as visitState, v.visitId as visitId " +
            "from Patient p join fetch Hospital h on p.hospital = h left join Visit v on v.patient = p " +
            "order by v.visitDate desc")
    List<Map<String, Object>> searchAllReservation();

    @Query("select p from Patient p join fetch Hospital h on p.hospital = h where p.patientId = :id")
    Patient detail(Long id);

    @Query("select " +
            "p.patientId as id, p.patientName as patientName, p.hospital.hospitalId as hospital, " +
            "p.sexualCode as sexualCode, p.patientEnrollNum as patientEnrollNum, " +
            "p.birth as birth, p.phone as phone, v.visitDate as visitDate, " +
            "v.visitState as visitState, v.visitId as visitId " +
            "from Patient p join fetch Hospital h on p.hospital = h left join Visit v on v.patient = p " +
            "where p.patientId = :id and v.visitId = :visitId " +
            "order by v.visitDate desc")
    Map<String, Object> detail(Long id, Long visitId);
}
