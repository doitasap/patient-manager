package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientByPatientName(String patientName);

    @Query("select p from Patient p join fetch Hospital h on p.hospital = h")
    List<Patient> searchAll();

    @Query("select p from Patient p join fetch Hospital h on p.hospital = h where p.patientId = :id")
    Patient detail(Long id);
}
