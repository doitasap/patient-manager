package doitasap.me.patient.repository;

import doitasap.me.patient.domain.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CodeRepository extends JpaRepository<Code, String> {

    @Query("select c from Code c join fetch CodeGroup cg on c.codeGroup = cg")
    List<Code> searchAllCodes();
}
