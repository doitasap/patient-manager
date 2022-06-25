package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    Optional<Patient> findPatientByPatientName(String patientName);

}
