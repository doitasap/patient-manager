package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VisitRepository extends JpaRepository<Visit, Long> {
    @Query("select v from Visit v join fetch Hospital h on v.hospital = h where h.hospitalName = :hospitalName")
    List<Visit> findAllByHospitalName(String hospitalName);

    @Query("select v from Visit v join fetch Patient p on v.patient = p where p.patientId = :patientId")
    List<Visit> findAllByPatient(Long patientId);
}

