package doitasap.me.patient.service;

import doitasap.me.patient.dto.HospitalDto;
import doitasap.me.patient.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022-06-26 오전 1:18
 * author DoitA$ap
 */
@Service
@RequiredArgsConstructor
public class HospitalService {
    private final HospitalRepository hospitalRepository;

    public List<HospitalDto> searchAll(){
        return hospitalRepository.findAll().stream().map(HospitalDto::new).collect(Collectors.toList());
    }
}
