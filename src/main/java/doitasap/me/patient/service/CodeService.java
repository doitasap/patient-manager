package doitasap.me.patient.service;

import doitasap.me.patient.dto.CodeDto;
import doitasap.me.patient.repository.CodeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 2022-06-26 오전 12:36
 * author DoitA$ap
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CodeService {
    private final CodeRepository codeRepository;

    public List<CodeDto> searchAllCodes(){
        return codeRepository.searchAllCodes().stream().map(CodeDto::new).collect(Collectors.toList());
    }
}
