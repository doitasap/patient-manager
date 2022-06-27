package doitasap.me.patient.repository.querydsl;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import doitasap.me.patient.criterion.PatientApiCriterion;
import doitasap.me.patient.criterion.PatientCriterion;
import doitasap.me.patient.dto.*;
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
                        eqHospitalId(criterion.getSearchHospitalId()),
                        containsPatientName(criterion.getSearchPatientName()),
                        eqPatientEnrollNum(criterion.getSearchEnrollNum()),
                        eqPatientBirth(criterion.getSearchBirth())
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
                        eqHospitalId(criterion.getSearchHospitalId()),
                        containsPatientName(criterion.getSearchPatientName()),
                        eqPatientEnrollNum(criterion.getSearchEnrollNum()),
                        eqPatientBirth(criterion.getSearchBirth())
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
                                eqHospitalId(criterion.getSearchHospitalId()),
                                containsPatientName(criterion.getSearchPatientName()),
                                eqPatientEnrollNum(criterion.getSearchEnrollNum()),
                                eqPatientBirth(criterion.getSearchBirth())
                        )
                        .fetchOne()
        );
        return new PageImpl<>(list,
                PageRequest.of(criterion.getPageNum() - 1, criterion.getRowCount()),
                totalCount);
    }

    @Override
    public Page<PatientApiDto> searchAllForApi(PatientApiCriterion criterion) {
        List<PatientApiDto> list = query
                .select(new QPatientApiDto(patient.patientId, patient.patientName, patient.patientEnrollNum, patient.sexualCode,
                        patient.birth, patient.phone, visit.visitDate.max().as("recentDate")))
                .from(patient)
                .leftJoin(patient.visit, visit)
                .where(
                        containsPatientName(criterion.getSearchPatientName()),
                        eqPatientEnrollNum(criterion.getSearchEnrollNum()),
                        eqPatientBirth(criterion.getSearchBirth())
                )
                .offset(criterion.getOffset())
                .limit(criterion.getPageSize())
                .groupBy(patient.patientId)
                .fetch();

        Long totalCount = Objects.requireNonNull(query.select(patient.count())
                .from(patient)
                .where(
                        containsPatientName(criterion.getSearchPatientName()),
                        eqPatientEnrollNum(criterion.getSearchEnrollNum()),
                        eqPatientBirth(criterion.getSearchBirth())
                )
                .fetchOne());

        return new PageImpl<>(list, PageRequest.of(criterion.getPageNo() - 1, criterion.getPageSize()), totalCount);
    }

    private BooleanExpression containsPatientName(String patientName) {
        return StringUtils.hasText(patientName) ?
                patient.patientName.contains(patientName) : null;
    }

    private BooleanExpression eqHospitalId(Long hospitalId) {
        return Objects.nonNull(hospitalId) ?
                patient.hospital().hospitalId.eq(hospitalId) : null;
    }

    private BooleanExpression eqPatientEnrollNum(String enrollNum) {
        return StringUtils.hasText(enrollNum) ?
                patient.patientEnrollNum.eq(enrollNum) : null;
    }

    private BooleanExpression eqPatientBirth(String birth) {
        return StringUtils.hasText(birth) ?
                patient.birth.eq(birth) : null;
    }
}
