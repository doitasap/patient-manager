package doitasap.me.patient.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.PatientDto;
import doitasap.me.patient.dto.QPatientDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;

import static doitasap.me.patient.domain.QHospital.hospital;
import static doitasap.me.patient.domain.QPatient.patient;
import static doitasap.me.patient.domain.QVisit.visit;

/**
 * 2022-06-27 오전 9:11
 * author DoitA$ap
 */
@RequiredArgsConstructor
public class PatientQueryDSLRepositoryImpl implements PatientQueryDSLRepository {

    private final JPAQueryFactory query;

    @Override
    public List<PatientDto> searchAll(PatientCriterion criterion) {
        return query
                .select(new QPatientDto(patient))
                .from(patient)
                .innerJoin(patient.hospital(), hospital)
                .fetchJoin()
                .where(
                        eqHospitalId(criterion),
                        containsPatientName(criterion),
                        eqPatientEnrollNum(criterion),
                        eqPatientBirth(criterion)
                )
                .orderBy(patient.patientName.asc())
                .fetch();
    }

    @Override
    public Page<PatientDto> searchAllReservation(PatientCriterion criterion) {
        List<PatientDto> list = query
                .select(
                        new QPatientDto(patient.patientId.as("id"), patient.patientName, hospital.hospitalId.as("hospital"),
                                patient.sexualCode, patient.patientEnrollNum, patient.birth, patient.phone,
                                visit.visitDate, visit.visitState, visit.visitId)
                )
                .from(patient)
                .innerJoin(patient.hospital(), hospital)
                .leftJoin(patient.visit, visit)
                .where(
                        eqHospitalId(criterion),
                        containsPatientName(criterion),
                        eqPatientEnrollNum(criterion),
                        eqPatientBirth(criterion)
                )
                .offset(criterion.getOffset())
                .limit(criterion.getRowCount())
                .orderBy(visit.visitDate.desc())
                .fetch();
        long totalCount = Objects.requireNonNull(
                query
                        .select(patient.count())
                        .from(patient)
                        .innerJoin(patient.hospital(), hospital)
                        .leftJoin(patient.visit, visit)
                        .where(
                                eqHospitalId(criterion),
                                containsPatientName(criterion),
                                eqPatientEnrollNum(criterion),
                                eqPatientBirth(criterion)
                        )
                        .fetchOne()
        );
        return new PageImpl<>(list,
                PageRequest.of(criterion.getPageNum() - 1, criterion.getRowCount()),
                totalCount);
    }

    private BooleanExpression containsPatientName(PatientCriterion criterion) {
        return StringUtils.hasText(criterion.getSearchPatientName()) ?
                patient.patientName.contains(criterion.getSearchPatientName()) : null;
    }

    private BooleanExpression eqHospitalId(PatientCriterion criterion) {
        return Objects.nonNull(criterion.getSearchHospitalId()) ?
                patient.hospital().hospitalId.eq(criterion.getSearchHospitalId()) : null;
    }

    private BooleanExpression eqPatientEnrollNum(PatientCriterion criterion) {
        return StringUtils.hasText(criterion.getSearchEnrollNum()) ?
                patient.patientEnrollNum.eq(criterion.getSearchEnrollNum()) : null;
    }

    private BooleanExpression eqPatientBirth(PatientCriterion criterion) {
        return StringUtils.hasText(criterion.getSearchBirth()) ?
                patient.birth.eq(criterion.getSearchBirth()) : null;
    }
}
